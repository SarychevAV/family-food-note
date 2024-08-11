package ru.asarychev.family_food_note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asarychev.family_food_note.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
