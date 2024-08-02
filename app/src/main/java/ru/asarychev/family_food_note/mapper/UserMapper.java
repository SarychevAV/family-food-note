package ru.asarychev.family_food_note.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.asarychev.family_food_note.dto.SignupRequest;
import ru.asarychev.family_food_note.entity.User;

@Mapper
@Component
public interface UserMapper {


    @Mapping(target = "password", source = "password", qualifiedByName = "encryptPassword")
    User dtoToEntity(SignupRequest signupRequest);

    @Named("encryptPassword")
    default String encryptPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
