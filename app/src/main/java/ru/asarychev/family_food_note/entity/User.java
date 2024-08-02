package ru.asarychev.family_food_note.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;
}
