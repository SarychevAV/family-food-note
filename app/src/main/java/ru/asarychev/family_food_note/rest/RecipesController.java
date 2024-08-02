package ru.asarychev.family_food_note.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.asarychev.api.RecipesApi;
import ru.asarychev.model.Recipe;

import java.util.List;

@RestController
public class RecipesController implements RecipesApi {

    @Override
    public ResponseEntity<Recipe> createRecipe(Recipe recipe) {
        return RecipesApi.super.createRecipe(recipe);
    }

    @Override
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return RecipesApi.super.getAllRecipes();
    }

    @Override
    public ResponseEntity<Recipe> getRecipeById(Integer recipeId) {
        return RecipesApi.super.getRecipeById(recipeId);
    }
}
