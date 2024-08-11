package ru.asarychev.family_food_note.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.asarychev.api.RecipesApi;
import ru.asarychev.family_food_note.entity.Recipe;
import ru.asarychev.family_food_note.mapper.RecipeMapper;
import ru.asarychev.family_food_note.repository.RecipeRepository;
import ru.asarychev.model.RecipeDto;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class RecipeController implements RecipesApi {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    @Override
    public ResponseEntity<RecipeDto> createRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        recipe.getSteps().forEach(cookStep -> cookStep.setRecipe(recipe));
        Recipe rs = recipeRepository.save(recipe);
        return ResponseEntity.ok(recipeMapper.toDto(rs));
    }

    @Override
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> rs = recipeRepository.findAll().stream().map(recipeMapper::toDto).toList();
        return ResponseEntity.ok(rs);
    }
}
