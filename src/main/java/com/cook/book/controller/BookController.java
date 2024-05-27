package com.cook.book.controller;

import com.cook.book.message.RecipeMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/api/recipes")
@Validated
@Tag(name = "Cookbook", description = "Cookbook API")
public interface BookController {

    @Operation(summary = "Find recipes")
    @GetMapping(produces = {"application/json"})
    Page<RecipeMessage> findRecipes(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @RequestParam(required = false) String title,
        @Schema(description = "The number of servings, must be greater than zero")
        @Min(1)
        @RequestParam(required = false) Integer servings,
        @Schema(description = "The key word to search in instructions")
        @RequestParam(required = false) String content,
        @Schema(description = "The category of recipe. If at least one ingredient is non-vegetarian, " +
            "the entire recipe is considered non-vegetarian", allowableValues = {"true", "false"})
        @RequestParam(required = false) Boolean vegetarian,
        @Schema(description = "The list of included ingredients")
        @RequestParam(required = false) List<String> include,
        @Schema(description = "The list of excluded ingredients")
        @RequestParam(required = false) List<String> exclude);

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    @Operation(summary = "Add new recipe")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recipe successfully created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "409", description = "Conflict"),
        @ApiResponse(responseCode = "500", description = "Internal error occurred")
    })
    ResponseEntity<RecipeMessage> createRecipe(@Valid @RequestBody RecipeMessage recipeRequestMessage);

    @PutMapping(produces = {"application/json"}, consumes = {"application/json"})
    @Operation(summary = "Update recipe")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe successfully updated"),
        @ApiResponse(responseCode = "404", description = "Recipe not found"),
        @ApiResponse(responseCode = "500", description = "Internal error occurred")
    })
    ResponseEntity<RecipeMessage> updateRecipe(@Valid @RequestBody RecipeMessage recipeRequestMessage);

    @DeleteMapping
    @Operation(summary = "Delete recipe by id")
    void deleteRecipe(@RequestParam Long id);
}
