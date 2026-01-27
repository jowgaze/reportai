package com.reportai.repository;

import com.reportai.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s.name from Student s where s.id = :id")
    String findNameById(Long id);
}