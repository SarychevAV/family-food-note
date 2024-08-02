package ru.asarychev.family_food_note.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.asarychev.api.IngredientsApi;
import ru.asarychev.model.Ingredient;

import java.util.List;

@RestController
public class IngredientsController implements IngredientsApi {

    @Override
    public ResponseEntity<Ingredient> createIngredient(Ingredient ingredient) {
        return IngredientsApi.super.createIngredient(ingredient);
    }

    @Override
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return IngredientsApi.super.getAllIngredients();
    }
}
