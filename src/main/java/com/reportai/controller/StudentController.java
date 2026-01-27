package com.reportai.controller;

import com.reportai.dto.daily.DailyRequestDto;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.dto.student.StudentRequestDto;
import com.reportai.dto.student.StudentResponseDto;
import com.reportai.entity.Daily;
import com.reportai.entity.Student;
import com.reportai.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody @Valid StudentRequestDto request){
        return ResponseEntity.ok(service.createStudent(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findStudentById(@PathVariable Long id){
        return ResponseEntity.ok(service.findStudentById(id));
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<String> generateReport(@PathVariable Long id){
        return ResponseEntity.ok(service.generateReport(id));
    }

    @PostMapping("/{id}/daily")
    public ResponseEntity<DailyResponseDto> createDaily(@PathVariable Long id, @RequestBody @Valid DailyRequestDto request){
        return ResponseEntity.ok(service.createDaily(id, request.content()));
    }

    @GetMapping("/{id}/daily")
    public ResponseEntity<List<DailyResponseDto>> findDailiesByStudent(@PathVariable Long id){
        return ResponseEntity.ok(service.findDailiesByStudent(id));
    }
}



