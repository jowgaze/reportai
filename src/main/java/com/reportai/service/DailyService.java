package com.reportai.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.reportai.dto.daily.DailyMapper;
import com.reportai.dto.daily.DailyRequestDto;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.config.AiConfig;
import com.reportai.dto.daily.DailyUpdateDto;
import com.reportai.entity.Daily;
import com.reportai.entity.Student;
import com.reportai.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
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
    private final DailyRepository dailyRepository;
    private final StudentService studentService;
    private final Client client;
    private final AiConfig aiConfig;

    @Transactional
    public DailyResponseDto create(DailyRequestDto request) {
        Student student = studentService.findById(request.studentId());
        Daily daily = new Daily(student, request.content());
        daily = dailyRepository.save(daily);
        return DailyMapper.toDto(daily);
    }

    @Transactional(readOnly = true)
    public DailyResponseDto findResponseById(Long id){
        Daily daily = findById(id);
        return DailyMapper.toDto(daily);
    }

    @Transactional(readOnly = true)
    public Daily findById(Long id){
        return dailyRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Daily with id %s not found", id))
        );
    }

    @Transactional
    public void delete(Long id){
        Daily daily = findById(id);
        dailyRepository.delete(daily);
    }

    @Transactional
    public void update(Long id, DailyUpdateDto request){
        Daily daily = findById(id);
        daily.setContent(request.content());
        dailyRepository.delete(daily);
    }

    @Transactional(readOnly = true)
    public List<DailyResponseDto> findAllByStudent(Long studentId) {
        return dailyRepository.findByStudent_Id(studentId)
                .stream()
                .map(DailyMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public String generateReport(Long id) {
        String name = dailyRepository.findStudentNameById(id);
        String dailies = dailiesToString(id);

        GenerateContentConfig config = generateContentConfig();
        GenerateContentResponse response = generateContentResponse(dailies, config);

        return Objects.requireNonNull(response.text()).replace("[NOME_DO_ALUNO]", name);
    }

    private GenerateContentConfig generateContentConfig() {
        return GenerateContentConfig
                .builder()
                .systemInstruction(Content.fromParts(Part.fromText(aiConfig.getPrompt().getSystem())))
                .build();
    }

    private GenerateContentResponse generateContentResponse(String dailies, GenerateContentConfig config) {
        return client
                .models
                .generateContent(
                        aiConfig.getModel(),
                        String.format(aiConfig.getPrompt().getRequest(), dailies),
                        config
                );
    }

    private String dailiesToString(Long studentId) {
        return findAllByStudent(studentId)
                .stream()
                .sorted(Comparator.comparing(DailyResponseDto::date))
                .map(d -> String.format("%s: %s", d.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), d.content()))
                .collect(Collectors.joining("\n"));
    }
}
