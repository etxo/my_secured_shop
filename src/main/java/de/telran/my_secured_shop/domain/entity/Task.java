package de.telran.my_secured_shop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "executed_at")
    private Timestamp executedAt;

    public Task(String description){
        this.description = description;
        executedAt = new Timestamp(System.currentTimeMillis());
    }

}
