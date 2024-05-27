package com.cook.book.mapper;

import com.cook.book.message.IngredientMessage;
import com.cook.book.message.RecipeMessage;
import com.cook.book.model.Category;
import com.cook.book.model.Ingredient;
import com.cook.book.model.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class RecipesMapperTest {

    @Autowired
    private RecipesMapper recipesMapper;

    @Test
    void toEntity() {
        RecipeMessage recipe = RecipeMessage.builder()
                                            .title("Potato salad")
                                            .servings(1)
                                            .instructions("Some instruction")
                                            .build();
        IngredientMessage ingredient = IngredientMessage.builder()
                                                        .name("Potato")
                                                        .category(Category.VEGETARIAN)
                                                        .build();
        List<IngredientMessage> ingredientResponseMessages = new ArrayList<>();
        ingredientResponseMessages.add(ingredient);
        recipe.setIngredients(ingredientResponseMessages);
        Recipe entity = recipesMapper.toEntity(recipe);
        assertNotNull(entity);
    }

    @Test
    void toMessage() {
        Recipe recipe = Recipe.builder()
                              .title("Potato salad")
                              .servings(1)
                              .instructions("Some instruction")
                              .build();
        Ingredient ingredient = Ingredient.builder()
                                          .id(42L)
                                          .name("Potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        recipe.getIngredients()
              .add(ingredient);
        RecipeMessage message = recipesMapper.toMessage(recipe);
        assertNotNull(message);
    }
}
