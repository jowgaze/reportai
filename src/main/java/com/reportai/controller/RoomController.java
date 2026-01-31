package com.reportai.controller;

import com.reportai.dto.room.RoomRequestDto;
import com.reportai.dto.room.RoomResponseDto;
import com.reportai.dto.room.RoomUpdateDto;
import com.reportai.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService service;

    @PostMapping
    public ResponseEntity<RoomResponseDto> create(@RequestBody @Valid RoomRequestDto request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> findAllBySchool(@RequestParam Long schoolId){
        return ResponseEntity.ok(service.findAllBySchool(schoolId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findResponseById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid RoomUpdateDto request){
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
