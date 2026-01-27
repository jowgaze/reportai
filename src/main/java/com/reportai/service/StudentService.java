package com.reportai.service;

import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.dto.student.StudentMapper;
import com.reportai.dto.student.StudentRequestDto;
import com.reportai.dto.student.StudentResponseDto;
import com.reportai.entity.Student;
import com.reportai.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final DailyService dailyService;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request) {
        Student student = new Student(request.name(), request.responsible(), request.dateOfBirth());
        student = repository.save(student);
        return StudentMapper.toDto(student);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto findStudentById(Long id) {
        Student student = findById(id);
        System.out.println(student);
        return StudentMapper.toDto(student);
    }

    @Transactional
    public DailyResponseDto createDaily(Long id, String content) {
        Student student = findById(id);
        return dailyService.createDaily(content, student);
    }

    @Transactional(readOnly = true)
    public List<DailyResponseDto> findDailiesByStudent(Long id) {
        return dailyService.findDailiesByStudentId(id);
    }

    @Transactional(readOnly = true)
    public String generateReport(Long id) {
        String name = repository.findNameById(id);
        return dailyService.generateReport(name, id);
    }

    private Student findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Student with id %s not found", id))
        );
    }
}
