package ru.asarychev.family_food_note.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CookStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long stepNumber;
    private String description;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
