package com.reportai.service;

import com.reportai.dto.daily.DailyRequestDto;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.dto.student.StudentRequestDto;
import com.reportai.entity.Daily;
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
    public Student create(StudentRequestDto request) {
        Student student = new Student(request.name());
        return repository.save(student);
    }

    @Transactional
    public Daily createDaily(DailyRequestDto request) {
        String content = request.content();
        Student student = findById(request.studentId());
        return dailyService.create(content, student);
    }

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Student with id %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public List<DailyResponseDto> findDailiesByStudent(Long id){
        return dailyService.findAllByStudentId(id);
    }
}
