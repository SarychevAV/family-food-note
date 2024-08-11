package ru.asarychev.family_food_note.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
