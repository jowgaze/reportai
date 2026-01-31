package com.reportai.repository;

import com.reportai.dto.student.StudentResponseDto;
import com.reportai.entity.Room;
import com.reportai.entity.Student;
import org.hibernate.sql.results.graph.FetchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsFindByRoom(Room room);

    List<Student> findAllByRoom_Id(Long roomId);
}