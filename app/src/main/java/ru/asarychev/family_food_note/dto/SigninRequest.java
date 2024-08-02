package ru.asarychev.family_food_note.dto;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
