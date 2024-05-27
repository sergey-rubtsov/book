package com.cook.book.repository;

import com.cook.book.model.Category;
import com.cook.book.model.Ingredient;
import com.cook.book.model.Recipe;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class RecipesRepositoryTest {

    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;

    @AfterEach
    void tearDown() {
        recipesRepository.deleteAll();
        ingredientsRepository.deleteAll();
    }

    @Test
    void saveRecipe() {
        Long recipeId = getRecipeId();
        assertNotNull(recipeId);
        Optional<Recipe> found = recipesRepository.findById(recipeId);
        assertTrue(found.isPresent());
        Recipe recipe = found.get();
        assertNotNull(recipe);
        assertEquals("Potato salad", recipe.getTitle());
        assertEquals("Potato", recipe.getIngredients()
                                     .get(0)
                                     .getName());
    }

    @Test
    void updateRecipe() {
        Long recipeId = getRecipeId();
        Optional<Recipe> found = recipesRepository.findById(recipeId);
        Recipe recipe = found.get();
        Ingredient ingredient = Ingredient.builder()
                                          .name("Schmotato")
                                          .category(Category.MEAT)
                                          .build();
        recipe.getIngredients()
              .add(ingredient);
        recipe.setServings(2);
        recipesRepository.save(recipe);
        found = recipesRepository.findById(recipeId);
        recipe = found.get();
        assertEquals(2, recipe.getIngredients()
                              .size());
        assertEquals(2, recipe.getServings());
    }

    @NotNull
    private Long getRecipeId() {
        Ingredient ingredient = Ingredient.builder()
                                          .name("Potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Recipe recipe = Recipe.builder()
                              .title("Potato salad")
                              .servings(1)
                              .instructions("Some instruction")
                              .build();
        recipe.getIngredients()
              .add(ingredient);
        recipesRepository.save(recipe);
        return recipe.getId();
    }
}