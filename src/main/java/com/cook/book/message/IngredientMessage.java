package com.cook.book.message;

import com.cook.book.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientMessage {

    @Schema(description = "The ingredient name is case sensitive and should be unique")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @Schema(description = "The category of ingredient. If at least one ingredient is non-vegetarian, " +
        "the entire recipe is considered non-vegetarian.", defaultValue = "MEAT")
    @Builder.Default
    private Category category = Category.MEAT;
}
