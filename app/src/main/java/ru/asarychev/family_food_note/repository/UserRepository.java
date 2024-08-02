package ru.asarychev.family_food_note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asarychev.family_food_note.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
