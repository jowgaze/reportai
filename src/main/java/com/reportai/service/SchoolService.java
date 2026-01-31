package com.reportai.service;

import com.reportai.dto.school.SchoolMapper;
import com.reportai.dto.school.SchoolRequestDto;
import com.reportai.dto.school.SchoolResponseDto;
import com.reportai.dto.school.SchoolUpdateDto;
import com.reportai.entity.School;
import com.reportai.repository.RoomRepository;
import com.reportai.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public SchoolResponseDto create(SchoolRequestDto request) {
        School school = SchoolMapper.toEntity(request);
        school = schoolRepository.save(school);
        return SchoolMapper.toDto(school);
    }

    @Transactional(readOnly = true)
    public SchoolResponseDto findResponseById(Long id) {
        School school = findById(id);
        return SchoolMapper.toDto(school);
    }

    @Transactional(readOnly = true)
    public List<SchoolResponseDto> findAll() {
        return schoolRepository.findAll()
                .stream()
                .map(SchoolMapper::toDto)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        School school = findById(id);
        if (roomRepository.existsBySchool(school))
            throw new RuntimeException("Cannot be deleted as there are active rooms");

        schoolRepository.delete(school);
    }

    @Transactional
    public void update(Long id, SchoolUpdateDto request) {
        School school = findById(id);
        school.setName(request.name());
        schoolRepository.save(school);
    }

    @Transactional(readOnly = true)
    public School findById(Long id) {
        return schoolRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("School with id %s not found", id))
        );
    }
}
