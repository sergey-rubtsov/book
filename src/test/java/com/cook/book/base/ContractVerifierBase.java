package com.cook.book.base;

import com.cook.book.BookApplication;
import com.cook.book.controller.BookController;
import com.cook.book.model.Category;
import com.cook.book.model.Ingredient;
import com.cook.book.model.Recipe;
import com.cook.book.repository.RecipesRepository;
import com.cook.book.service.RecipesService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BookApplication.class)
public abstract class ContractVerifierBase {

    @Autowired
    private BookController bookController;

    @Autowired
    private RecipesService recipesService;

    @Autowired
    private RecipesRepository recipesRepository;

    @AfterEach
    public void clean() {
        recipesRepository.deleteAll();
    }

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(bookController);
        Recipe recipe1 = Recipe.builder()
                               .title("Potato Salad")
                               .servings(1)
                               .instructions("Some oven instruction")
                               .build();
        Ingredient potato = Ingredient.builder()
                                      .name("Potato")
                                      .category(Category.VEGETARIAN)
                                      .build();
        Ingredient salmon = Ingredient.builder()
                                      .name("Salmon")
                                      .category(Category.MEAT)
                                      .build();
        recipe1.getIngredients().add(potato);
        recipe1.getIngredients().add(salmon);
        recipesService.createRecipe(recipe1);

        Ingredient justPotato = Ingredient.builder()
                                          .name("Potato")
                                          .category(Category.VEGETARIAN)
                                          .build();
        Ingredient meat = Ingredient.builder()
                                    .name("Meat")
                                    .category(Category.MEAT)
                                    .build();
        Recipe recipe2 = Recipe.builder()
                               .title("Just Salad")
                               .servings(2)
                               .instructions("Some instruction 2")
                               .build();
        recipe2.getIngredients().add(justPotato);
        recipe2.getIngredients().add(meat);
        recipesService.createRecipe(recipe2);

        Recipe recipe3 = Recipe.builder()
                               .title("Vegetarian Pasta")
                               .servings(3)
                               .instructions("Boil pasta, add sauce")
                               .build();
        Ingredient pasta = Ingredient.builder()
                                     .name("Pasta")
                                     .category(Category.VEGETARIAN)
                                     .build();
        Ingredient tomatoSauce = Ingredient.builder()
                                           .name("Tomato Sauce")
                                           .category(Category.VEGETARIAN)
                                           .build();
        recipe3.getIngredients().add(pasta);
        recipe3.getIngredients().add(tomatoSauce);
        recipesService.createRecipe(recipe3);

        Recipe recipe4 = Recipe.builder()
                               .title("Chicken Soup")
                               .servings(4)
                               .instructions("Boil chicken, add vegetables")
                               .build();
        Ingredient chicken = Ingredient.builder()
                                       .name("Chicken")
                                       .category(Category.MEAT)
                                       .build();
        Ingredient carrots = Ingredient.builder()
                                       .name("Carrots")
                                       .category(Category.VEGETARIAN)
                                       .build();
        recipe4.getIngredients().add(chicken);
        recipe4.getIngredients().add(carrots);
        recipesService.createRecipe(recipe4);

        Recipe recipe5 = Recipe.builder()
                               .title("Beef Stew")
                               .servings(5)
                               .instructions("Cook beef, add potatoes and carrots")
                               .build();
        Ingredient beef = Ingredient.builder()
                                    .name("Beef")
                                    .category(Category.MEAT)
                                    .build();
        Ingredient potatoes = Ingredient.builder()
                                        .name("Potatoes")
                                        .category(Category.VEGETARIAN)
                                        .build();
        Ingredient stewCarrots = Ingredient.builder()
                                           .name("Carrots")
                                           .category(Category.VEGETARIAN)
                                           .build();
        recipe5.getIngredients().add(beef);
        recipe5.getIngredients().add(potatoes);
        recipe5.getIngredients().add(stewCarrots);
        recipesService.createRecipe(recipe5);

        Recipe recipe6 = Recipe.builder()
                               .title("Fruit Salad")
                               .servings(2)
                               .instructions("Mix all fruits together")
                               .build();
        Ingredient apple = Ingredient.builder()
                                     .name("Apple")
                                     .category(Category.VEGETARIAN)
                                     .build();
        Ingredient banana = Ingredient.builder()
                                      .name("Banana")
                                      .category(Category.VEGETARIAN)
                                      .build();
        recipe6.getIngredients().add(apple);
        recipe6.getIngredients().add(banana);
        recipesService.createRecipe(recipe6);

        Recipe recipe7 = Recipe.builder()
                               .title("Fish Tacos")
                               .servings(3)
                               .instructions("Grill fish, add to tacos")
                               .build();
        Ingredient fish = Ingredient.builder()
                                    .name("Fish")
                                    .category(Category.MEAT)
                                    .build();
        Ingredient taco = Ingredient.builder()
                                    .name("Taco")
                                    .category(Category.VEGETARIAN)
                                    .build();
        recipe7.getIngredients().add(fish);
        recipe7.getIngredients().add(taco);
        recipesService.createRecipe(recipe7);

        Recipe recipe8 = Recipe.builder()
                               .title("Greek Salad")
                               .servings(4)
                               .instructions("Mix vegetables with feta cheese and olives")
                               .build();
        Ingredient cucumber = Ingredient.builder()
                                        .name("Cucumber")
                                        .category(Category.VEGETARIAN)
                                        .build();
        Ingredient feta = Ingredient.builder()
                                    .name("Feta Cheese")
                                    .category(Category.VEGETARIAN)
                                    .build();
        Ingredient olives = Ingredient.builder()
                                      .name("Olives")
                                      .category(Category.VEGETARIAN)
                                      .build();
        recipe8.getIngredients().add(cucumber);
        recipe8.getIngredients().add(feta);
        recipe8.getIngredients().add(olives);
        recipesService.createRecipe(recipe8);

        Recipe recipe9 = Recipe.builder()
                               .title("Egg Breakfast")
                               .servings(1)
                               .instructions("Scramble eggs, serve with toast")
                               .build();
        Ingredient egg = Ingredient.builder()
                                   .name("Egg")
                                   .category(Category.VEGETARIAN)
                                   .build();
        Ingredient toast = Ingredient.builder()
                                     .name("Toast")
                                     .category(Category.VEGETARIAN)
                                     .build();
        recipe9.getIngredients().add(egg);
        recipe9.getIngredients().add(toast);
        recipesService.createRecipe(recipe9);

        Recipe recipe10 = Recipe.builder()
                                .title("Lamb Curry")
                                .servings(5)
                                .instructions("Cook lamb with curry spices")
                                .build();
        Ingredient lamb = Ingredient.builder()
                                    .name("Lamb")
                                    .category(Category.MEAT)
                                    .build();
        Ingredient currySpices = Ingredient.builder()
                                           .name("Curry Spices")
                                           .category(Category.VEGETARIAN)
                                           .build();
        recipe10.getIngredients().add(lamb);
        recipe10.getIngredients().add(currySpices);
        recipesService.createRecipe(recipe10);
    }

}
