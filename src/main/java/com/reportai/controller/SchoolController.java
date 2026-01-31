package com.reportai.controller;

import com.reportai.dto.school.SchoolRequestDto;
import com.reportai.dto.school.SchoolResponseDto;
import com.reportai.dto.school.SchoolUpdateDto;
import com.reportai.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schools")
public class SchoolController {
    private final SchoolService service;

    @PostMapping
    public ResponseEntity<SchoolResponseDto> create(@RequestBody @Valid SchoolRequestDto request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SchoolResponseDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findResponseById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid SchoolUpdateDto request){
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
