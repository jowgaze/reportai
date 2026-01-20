package com.reportai.repository;

import com.reportai.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    List<Daily> findByStudent_Id(Long studentId);
}