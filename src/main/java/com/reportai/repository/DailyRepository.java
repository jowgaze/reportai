package com.reportai.repository;

import com.reportai.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    List<Daily> findByStudent_Id(Long studentId);

    @Query("select d.student.name from Daily d where d.student.id = :id")
    String findStudentNameById(Long id);
}