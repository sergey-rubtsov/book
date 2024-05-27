package com.cook.book.controller;

import com.cook.book.mapper.RecipesMapper;
import com.cook.book.message.RecipeMessage;
import com.cook.book.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

    @Autowired
    private RecipesService recipesService;

    @Autowired
    private RecipesMapper recipesMapper;

    @Override
    public Page<RecipeMessage> findRecipes(final Integer page,
                                           final Integer size,
                                           final String title,
                                           final Integer servings,
                                           final String content,
                                           final Boolean vegetarian,
                                           final List<String> include,
                                           final List<String> exclude) {
        return recipesService.findRecipes(title, servings, content, vegetarian, include, exclude,
                                 PageRequest.of(page, size, Sort.by("title")
                                                                .ascending()))
                             .map(recipe -> recipesMapper.toMessage(recipe));
    }

    @Override
    public ResponseEntity<RecipeMessage> createRecipe(final RecipeMessage recipeRequestMessage) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(recipesMapper.toMessage(
                                 recipesService.createRecipe(recipesMapper.toEntity(recipeRequestMessage))));
    }

    @Override
    public ResponseEntity<RecipeMessage> updateRecipe(final RecipeMessage recipeRequestMessage) {
        return ResponseEntity.ok(recipesMapper.toMessage(
            recipesService.updateRecipe(recipesMapper.toEntity(recipeRequestMessage))));
    }

    @Override
    public void deleteRecipe(final Long id) {
        recipesService.deleteRecipe(id);
    }
}
