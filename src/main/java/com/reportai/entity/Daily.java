package com.reportai.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Daily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String content;

    public Daily(Student student, String content){
        this.student = student;
        this.date = LocalDate.now();
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Daily daily = (Daily) o;
        return Objects.equals(id, daily.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
