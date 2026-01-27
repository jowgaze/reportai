package com.reportai.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.reportai.dto.daily.DailyMapper;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.entity.Daily;
import com.reportai.entity.Student;
import com.reportai.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyService {
    private final DailyRepository repository;
    private final Client client;

    @Value("${ai.model}")
    String aiModel;

    private static final String TEXT_CONFIG = "Você é um especialista em pedagogia e gera relatórios." +
            "Quando precisar falar o nome do aluno utilize [NOME_DO_ALUNO]." +
            "Escreva tudo em texto corrido em no máximo 7 linhas.";

    private static final String TEXT_REQUEST = "Analise os relatos diários abaixo e gere um relatório descritivo do comportamento do estudante: \n %s";

    @Transactional
    public DailyResponseDto createDaily(String content, Student student) {
        Daily daily = new Daily(student, content);
        daily = repository.save(daily);
        return DailyMapper.toDto(daily);
    }

    @Transactional(readOnly = true)
    public List<DailyResponseDto> findDailiesByStudentId(Long studentId) {
        return repository.findByStudent_Id(studentId)
                .stream()
                .map(DailyMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public String generateReport(String name, Long id) {
        String dailies = generateDailiesDetails(id);
        GenerateContentConfig config = generateContentConfig();
        GenerateContentResponse response = generateContentResponse(dailies, config);
        return Objects.requireNonNull(response.text()).replace("[NOME_DO_ALUNO]", name);
    }

    private GenerateContentConfig generateContentConfig() {
        return GenerateContentConfig
                .builder()
                .systemInstruction(Content.fromParts(Part.fromText(TEXT_CONFIG)))
                .build();
    }

    private GenerateContentResponse generateContentResponse(String dailies, GenerateContentConfig config) {
        return client.models.generateContent(aiModel, String.format(TEXT_REQUEST, dailies), config);
    }

    private String generateDailiesDetails(Long studentId) {
        return findDailiesByStudentId(studentId)
                .stream()
                .sorted(Comparator.comparing(DailyResponseDto::date))
                .map(d -> String.format("%s: %s", d.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), d.content()))
                .collect(Collectors.joining("\n"));
    }
}
