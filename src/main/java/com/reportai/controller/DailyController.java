package com.reportai.controller;

import com.reportai.dto.daily.DailyRequestDto;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.dto.daily.DailyUpdateDto;
import com.reportai.service.DailyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dailies")
public class DailyController {
    private final DailyService service;

    @PostMapping
    public ResponseEntity<DailyResponseDto> create(@RequestBody @Valid DailyRequestDto request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DailyResponseDto>> findAllByStudent(@RequestParam Long studentId){
        return ResponseEntity.ok(service.findAllByStudent(studentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findResponseById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid DailyUpdateDto request){
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<String> generateReport(@RequestParam Long studentId){
        return ResponseEntity.ok(service.generateReport(studentId));
    }
}
