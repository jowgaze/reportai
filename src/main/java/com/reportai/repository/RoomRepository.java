package com.reportai.repository;

import com.reportai.entity.Room;
import com.reportai.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllBySchool_Id(Long schoolId);

    boolean existsBySchool(School school);
}