package com.cook.book.mapper;

import com.cook.book.message.IngredientMessage;
import com.cook.book.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RecipesMapper.class)
public interface IngredientMapper {

    @Mapping(target = "id", ignore = true)
    Ingredient toEntity(IngredientMessage message);

    IngredientMessage toMessage(Ingredient entity);
}
