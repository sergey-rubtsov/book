package com.cook.book.service;

import com.cook.book.exception.BadRequestException;
import com.cook.book.exception.NotFoundException;
import com.cook.book.model.Category;
import com.cook.book.model.Ingredient;
import com.cook.book.model.Recipe;
import com.cook.book.repository.IngredientsRepository;
import com.cook.book.repository.RecipesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class RecipesServiceTest {

    @Autowired
    private RecipesService recipesService;
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
    void searchRecipes() {
        Recipe recipe = Recipe.builder()
                              .title("Potato salad")
                              .servings(1)
                              .instructions("Some oven instruction")
                              .build();
        Ingredient potato = Ingredient.builder()
                                      .name("Potato")
                                      .category(Category.VEGETARIAN)
                                      .build();
        Ingredient schmotato = Ingredient.builder()
                                         .name("Salmon")
                                         .category(Category.MEAT)
                                         .build();
        recipe.getIngredients()
              .add(potato);
        recipe.getIngredients()
              .add(schmotato);
        recipesService.createRecipe(recipe);
        Ingredient justPotato = Ingredient.builder()
                                          .name("Potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Ingredient meat = Ingredient.builder()
                                    .name("Meat")
                                    .category(Category.MEAT)
                                    .build();
        Recipe recipe2 = Recipe.builder()
                               .title("Just salad")
                               .servings(2)
                               .instructions("Some instruction 2")
                               .build();
        recipe2.getIngredients()
               .add(justPotato);
        recipe2.getIngredients()
               .add(meat);
        recipesService.createRecipe(recipe2);
        Ingredient fish = Ingredient.builder()
                                    .name("Fish")
                                    .category(Category.MEAT)
                                    .build();
        Recipe recipe3 = Recipe.builder()
                               .title("Fish")
                               .servings(2)
                               .instructions("Some instruction 3")
                               .build();
        recipe3.getIngredients()
               .add(fish);
        recipesService.createRecipe(recipe3);
        Ingredient vegetable0 = Ingredient.builder()
                                          .name("Vegetable0")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Ingredient vegetable1 = Ingredient.builder()
                                          .name("Vegetable1")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Recipe recipe4 = Recipe.builder()
                               .title("Vegetarian0")
                               .servings(2)
                               .instructions("Some instruction 4")
                               .build();
        recipe4.getIngredients()
               .add(vegetable0);
        recipe4.getIngredients()
               .add(vegetable1);
        recipesService.createRecipe(recipe4);
        Ingredient vegetable2 = Ingredient.builder()
                                          .name("Vegetable0")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Ingredient vegetable3 = Ingredient.builder()
                                          .name("Potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Recipe recipe5 = Recipe.builder()
                               .title("Vegetarian1")
                               .servings(5)
                               .instructions("Some oven instruction 4")
                               .build();
        recipe5.getIngredients()
               .add(vegetable2);
        recipe5.getIngredients()
               .add(vegetable3);
        recipesService.createRecipe(recipe5);
        Page<Recipe> found;
        found = recipesService.findRecipes(
            "Just salad",
            null,
            null,
            null,
            null,
            null, Pageable.ofSize(10));
        assertEquals(1, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            null,
            null,
            null,
            null, Pageable.ofSize(10));
        assertEquals(5, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            null,
            null,
            List.of("Potato"),
            null,
            Pageable.ofSize(10));
        assertEquals(3, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            null,
            null,
            List.of("Fish", "Meat"),
            null,
            Pageable.ofSize(10));
        assertEquals(2, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            null,
            null,
            null,
            List.of("Meat"), Pageable.ofSize(10));
        assertEquals(4, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            null,
            true,
            null,
            null, Pageable.ofSize(10));
        assertEquals(2, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            null,
            true,
            null,
            List.of("Potato"), Pageable.ofSize(10));
        assertEquals(1, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            8,
            null,
            true,
            null,
            List.of("Potato"), Pageable.ofSize(10));
        assertEquals(0, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            "oven",
            true,
            null,
            null, Pageable.ofSize(10));
        assertEquals(1, found.stream()
                             .count());
        found = recipesService.findRecipes(
            null,
            null,
            "oven",
            null,
            null,
            null, Pageable.ofSize(10));
        assertEquals(2, found.stream()
                             .count());
    }

    @Test
    void createRecipe() {
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
        recipesService.createRecipe(recipe);
        Optional<Recipe> optionalRecipe = recipesRepository.findOneByTitle("Potato salad");
        assertTrue(optionalRecipe.isPresent());
        Ingredient duplicatedIngredient = Ingredient.builder()
                                                    .name("Potato")
                                                    .category(Category.VEGETARIAN)
                                                    .build();
        Recipe duplicatedRecipe = Recipe.builder()
                                        .title("Potato salad")
                                        .servings(2)
                                        .instructions("Some instruction 2")
                                        .build();
        duplicatedRecipe.getIngredients()
                        .add(duplicatedIngredient);
        assertThrows(BadRequestException.class, () -> recipesService.createRecipe(duplicatedRecipe));
    }

    @Test
    void updateRecipe() {
        Recipe recipe = Recipe.builder()
                              .title("Potato salad")
                              .servings(1)
                              .instructions("Some instruction")
                              .build();
        Ingredient potato = Ingredient.builder()
                                      .name("Potato")
                                      .category(Category.VEGETARIAN)
                                      .build();
        Ingredient schmotato = Ingredient.builder()
                                         .name("Schmotato")
                                         .category(Category.MEAT)
                                         .build();
        Ingredient unknown = Ingredient.builder()
                                       .name("Unknown")
                                       .category(Category.MEAT)
                                       .build();
        recipe.getIngredients()
              .add(potato);
        recipe.getIngredients()
              .add(schmotato);
        recipe.getIngredients()
              .add(unknown);
        recipesService.createRecipe(recipe);
        Ingredient justPotato = Ingredient.builder()
                                          .name("Just potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Ingredient schmotato2 = Ingredient.builder()
                                          .name("Schmotato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Recipe updatedByTitle = Recipe.builder()
                                      .title("Potato salad not found")
                                      .servings(2)
                                      .instructions("Some instruction 2")
                                      .build();
        updatedByTitle.getIngredients()
                      .add(justPotato);
        updatedByTitle.getIngredients()
                      .add(schmotato2);
        assertThrowsExactly(NotFoundException.class, () -> recipesService.updateRecipe(updatedByTitle));
        updatedByTitle.setTitle("Potato salad");
        Recipe result = recipesService.updateRecipe(updatedByTitle);
        assertEquals(2, result.getServings());
        assertEquals(2, result.getIngredients()
                              .size());
        assertEquals("Just potato", result.getIngredients()
                                          .get(0)
                                          .getName());
        Long id = result.getId();
        Recipe updatedById = Recipe.builder()
                                   .id(id)
                                   .title("Potato salad not found")
                                   .servings(2)
                                   .instructions("Some instruction 2")
                                   .build();
        Optional<Recipe> optionalRecipe = recipesRepository.findOneByTitle("Potato salad");
        assertTrue(optionalRecipe.isPresent());
        result = optionalRecipe.get();
        assertEquals(2, result.getServings());
        assertEquals(2, result.getIngredients()
                              .size());
        assertEquals("Just potato", result.getIngredients()
                                          .get(0)
                                          .getName());
    }

    @Test
    void deleteRecipe() {
        Recipe recipe = Recipe.builder()
                              .title("Potato salad")
                              .servings(1)
                              .instructions("Some instruction")
                              .build();
        Ingredient potato = Ingredient.builder()
                                      .name("Potato")
                                      .category(Category.VEGETARIAN)
                                      .build();
        Ingredient schmotato = Ingredient.builder()
                                         .name("Schmotato")
                                         .category(Category.MEAT)
                                         .build();
        recipe.getIngredients()
              .add(potato);
        recipe.getIngredients()
              .add(schmotato);
        Long id = recipesService.createRecipe(recipe)
                                .getId();
        Ingredient justPotato = Ingredient.builder()
                                          .name("Potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Ingredient meat = Ingredient.builder()
                                    .name("Meat")
                                    .category(Category.MEAT)
                                    .build();
        Recipe recipe2 = Recipe.builder()
                               .title("Just salad")
                               .servings(2)
                               .instructions("Some instruction 2")
                               .build();
        recipe2.getIngredients()
               .add(justPotato);
        recipe2.getIngredients()
               .add(meat);
        Long id2 = recipesService.createRecipe(recipe2)
                                 .getId();
        assertEquals(2, recipesRepository.count());
        assertEquals(3, ingredientsRepository.count());
        recipesService.deleteRecipe(id);
        assertEquals(1, recipesRepository.count());
        recipesService.deleteRecipe(id2);
        assertEquals(0, recipesRepository.count());
    }
}
