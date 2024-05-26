package com.cook.book.controller;

import com.cook.book.mapper.RecipesMapper;
import com.cook.book.message.RecipeMessage;
import com.cook.book.model.Recipe;
import com.cook.book.service.RecipesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RecipesControllerTest {

    @Mock
    private RecipesService recipesService;

    @Mock
    private RecipesMapper recipesMapper;

    @InjectMocks
    private final BookController testObj = new BookControllerImpl();

    @Test
    void searchRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(mock(Recipe.class));
        Page<Recipe> mock = new PageImpl<>(recipes);
        Mockito.when(recipesService.findRecipes(
                ArgumentMatchers.eq("title"),
                ArgumentMatchers.eq(1),
                ArgumentMatchers.eq("content"),
                ArgumentMatchers.eq(true),
                ArgumentMatchers.anyList(),
                ArgumentMatchers.anyList(),
                any(Pageable.class)
        )).thenReturn(mock);
        Mockito.when(recipesMapper.toMessage(any(Recipe.class))).thenReturn(mock(RecipeMessage.class));
        testObj.findRecipes(
                "title",
                1,
                "content",
                true,
                List.of("include"),
                List.of("exclude"),
                Pageable.unpaged());
        Mockito.verify(recipesMapper, times(1)).toMessage(any(Recipe.class));
    }

    @Test
    void createRecipe() {
        Mockito.when(recipesMapper.toEntity(any(RecipeMessage.class))).thenReturn(mock(Recipe.class));
        Mockito.when(recipesMapper.toMessage(any(Recipe.class))).thenReturn(mock(RecipeMessage.class));
        Mockito.when(recipesService.createRecipe(any(Recipe.class))).thenReturn(mock(Recipe.class));
        testObj.createRecipe(mock(RecipeMessage.class));
        Mockito.verify(recipesMapper, times(1)).toMessage(any(Recipe.class));
        Mockito.verify(recipesMapper, times(1)).toEntity(any(RecipeMessage.class));
        Mockito.verify(recipesService, times(1)).createRecipe(any(Recipe.class));
    }

    @Test
    void updateRecipe() {
        Mockito.when(recipesMapper.toEntity(any(RecipeMessage.class))).thenReturn(mock(Recipe.class));
        Mockito.when(recipesMapper.toMessage(any(Recipe.class))).thenReturn(mock(RecipeMessage.class));
        Mockito.when(recipesService.updateRecipe(any(Recipe.class))).thenReturn(mock(Recipe.class));
        testObj.updateRecipe(mock(RecipeMessage.class));
        Mockito.verify(recipesMapper, times(1)).toMessage(any(Recipe.class));
        Mockito.verify(recipesMapper, times(1)).toEntity(any(RecipeMessage.class));
        Mockito.verify(recipesService, times(1)).updateRecipe(any(Recipe.class));
    }

    @Test
    void deleteRecipe() {
        doNothing().when(recipesService).deleteRecipe(1L);
        testObj.deleteRecipe(1L);
        Mockito.verify(recipesService, times(1)).deleteRecipe(1L);
    }
}