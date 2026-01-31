package com.reportai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Room> rooms = new ArrayList<>();

    public School(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(id, school.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
