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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Student> students = new ArrayList<>();

    public Room(String name, School school){
        this.name = name;
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
