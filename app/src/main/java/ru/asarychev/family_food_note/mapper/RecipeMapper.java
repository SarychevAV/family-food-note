package ru.asarychev.family_food_note.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.asarychev.family_food_note.entity.Recipe;
import ru.asarychev.model.RecipeDto;

@Mapper
@Component
public interface RecipeMapper {

    Recipe toEntity(RecipeDto recipeDto);

    RecipeDto toDto(Recipe recipe);

}
