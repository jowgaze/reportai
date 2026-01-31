package com.reportai.service;

import com.reportai.dto.student.StudentMapper;
import com.reportai.dto.student.StudentRequestDto;
import com.reportai.dto.student.StudentResponseDto;
import com.reportai.dto.student.StudentUpdateDto;
import com.reportai.entity.Room;
import com.reportai.entity.Student;
import com.reportai.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RoomService roomService;

    @Transactional
    public StudentResponseDto create(StudentRequestDto request) {
        Room room = roomService.findById(request.roomId());
        Student student = StudentMapper.toEntity(request, room);
        student = studentRepository.save(student);
        return StudentMapper.toDto(student);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto findResponseById(Long id) {
        Student student = findById(id);
        return StudentMapper.toDto(student);
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDto> findAllByRoom(Long roomId){
        return studentRepository.findAllByRoom_Id(roomId)
                .stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @Transactional
    public void update(Long id, StudentUpdateDto request) {
        Student student = findById(id);

        if (request.name() != null)
            student.setName(request.name());

        if (request.responsible() != null)
            student.setResponsible(request.responsible());

        if (request.dateOfBirth() != null)
            student.setDateOfBirth(request.dateOfBirth());

        studentRepository.save(student);
    }

    @Transactional
    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Student with id %s not found", id))
        );
    }
}
