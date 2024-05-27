package com.cook.book.mapper;

import com.cook.book.message.RecipeMessage;
import com.cook.book.model.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface RecipesMapper {

    Recipe toEntity(RecipeMessage message);

    RecipeMessage toMessage(Recipe entity);
}
