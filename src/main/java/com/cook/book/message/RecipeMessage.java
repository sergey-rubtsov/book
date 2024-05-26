package com.cook.book.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeMessage {

    @Schema(description = "The recipe id")
    private Long id;

    @Schema(description = "The recipe title is case sensitive and should be unique")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String title;

    @Schema(description = "The number of servings, must be greater than zero")
    @JsonProperty(required = true)
    @Min(1)
    private Integer servings;

    @Schema(description = "The instructions contain quantitative measures of the ingredients and steps for " +
            "preparing the dish. It is possible to search for a keyword in the instruction text.")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String instructions;

    @Schema(description = "The list of ingredients")
    @JsonProperty(required = true)
    @NotNull
    private List<IngredientMessage> ingredients;

}
