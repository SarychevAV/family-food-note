package ru.asarychev.family_food_note.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asarychev.family_food_note.JwtCore;
import ru.asarychev.family_food_note.dto.SigninRequest;
import ru.asarychev.family_food_note.dto.SignupRequest;
import ru.asarychev.family_food_note.entity.User;
import ru.asarychev.family_food_note.mapper.UserMapper;
import ru.asarychev.family_food_note.repository.UserRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class SecurityController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;


    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Имя пользователя занято");
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Выберите другую электронную почту");
        }

        User user = userMapper.dtoToEntity(signupRequest);
        userRepository.save(user);
        return ResponseEntity.ok("Success");

    }

    @PostMapping("/signin")
    ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);

        return ResponseEntity.ok(jwt);
    }
}
