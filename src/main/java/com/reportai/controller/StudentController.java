package com.reportai.controller;

import com.reportai.dto.daily.DailyRequestDto;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.dto.student.StudentRequestDto;
import com.reportai.entity.Daily;
import com.reportai.entity.Student;
import com.reportai.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping("/student")
    public ResponseEntity<Student> create(@RequestBody @Valid StudentRequestDto request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/student/{student_id}")
    public ResponseEntity<Student> findById(@PathVariable("student_id") Long studentId){
        return ResponseEntity.ok(service.findById(studentId));
    }

    @PostMapping("/daily")
    public ResponseEntity<Daily> createDaily(@RequestBody @Valid DailyRequestDto request){
        return ResponseEntity.ok(service.createDaily(request));
    }

    @GetMapping("/dailies/{student_id}")
    public ResponseEntity<List<DailyResponseDto>> findDailiesByStudent(@PathVariable("student_id") Long studentId){
        return ResponseEntity.ok(service.findDailiesByStudent(studentId));
    }

}
