package com.cook.book.controller;

import com.cook.book.message.RecipeMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

    @Override
    public Page<RecipeMessage> findRecipes(final String title, final Integer servings, final String content, final Boolean vegetarian, final List<String> include, final List<String> exclude, final Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<RecipeMessage> createRecipe(final RecipeMessage recipeMessage) {
        return null;
    }

    @Override
    public ResponseEntity<RecipeMessage> updateRecipe(final Long id, final RecipeMessage recipeMessage) {
        return null;
    }

    @Override
    public void deleteRecipe(final Long id) {

    }
}
