package com.cook.book.service;

import com.cook.book.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipesService {

    Page<Recipe> findRecipes(
        String title,
        Integer servings,
        String content,
        Boolean vegetarian,
        List<String> include,
        List<String> exclude,
        Pageable pageable);

    Recipe createRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe updated);

    void deleteRecipe(Long id);
}
