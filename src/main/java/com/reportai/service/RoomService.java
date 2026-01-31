package com.reportai.service;

import com.reportai.dto.room.RoomMapper;
import com.reportai.dto.room.RoomRequestDto;
import com.reportai.dto.room.RoomResponseDto;
import com.reportai.dto.room.RoomUpdateDto;
import com.reportai.entity.Room;
import com.reportai.entity.School;
import com.reportai.repository.RoomRepository;
import com.reportai.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final SchoolService schoolService;

    @Transactional
    public RoomResponseDto create(RoomRequestDto request) {
        School school = schoolService.findById(request.schoolId());
        Room room = RoomMapper.toEntity(request, school);
        room = roomRepository.save(room);
        return RoomMapper.toDto(room);
    }

    @Transactional(readOnly = true)
    public RoomResponseDto findResponseById(Long id) {
        Room room = findById(id);
        return RoomMapper.toDto(room);
    }

    @Transactional(readOnly = true)
    public List<RoomResponseDto> findAllBySchool(Long schoolId) {
        return roomRepository.findAllBySchool_Id(schoolId)
                .stream()
                .map(RoomMapper::toDto)
                .toList();
    }

    @Transactional
    public void delete(Long id){
        Room room = findById(id);
        if (studentRepository.existsFindByRoom(room))
            throw new RuntimeException("Cannot be deleted as there are active students");

        roomRepository.delete(room);
    }

    @Transactional
    public void update(Long id, RoomUpdateDto request) {
        Room room = findById(id);
        room.setName(request.name());
        roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Room with id %s not found", id))
        );
    }
}
