package com.cook.book.service;

import com.cook.book.exception.BadRequestException;
import com.cook.book.exception.NotFoundException;
import com.cook.book.model.Category;
import com.cook.book.model.Ingredient;
import com.cook.book.model.Recipe;
import com.cook.book.repository.IngredientsRepository;
import com.cook.book.repository.RecipesRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipesServiceImpl implements RecipesService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public Page<Recipe> findRecipes(
        String title,
        Integer servings,
        String content,
        Boolean vegetarian,
        List<String> include,
        List<String> exclude,
        Pageable pageable) {
        Specification<Recipe> spec = buildSpecification(title, servings, content, vegetarian, include, exclude);
        return recipesRepository.findAll(spec, pageable);
    }

    private Specification<Recipe> buildSpecification(String title,
                                                     Integer servings,
                                                     String content,
                                                     Boolean vegetarian,
                                                     List<String> include,
                                                     List<String> exclude) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(title)
                    .ifPresent(t -> predicates.add(builder.equal(root.get("title"), t)));
            Optional.ofNullable(servings)
                    .ifPresent(s -> predicates.add(builder.equal(root.get("servings"), s)));
            Optional.ofNullable(content)
                    .ifPresent(s -> predicates.add(builder.like(root.get("instructions"),
                        String.format("%%%s%%", s))));
            Optional.ofNullable(vegetarian)
                    .ifPresent(veg -> {
                        if (Boolean.TRUE.equals(veg)) {
                            Subquery<Long> subQuery = query.subquery(Long.class);
                            Join<Recipe, Ingredient> join = subQuery.from(Recipe.class)
                                                                    .join("ingredients");
                            subQuery.select(join.getParent()
                                                .get("id"));
                            subQuery.where(join.get("category")
                                               .in(Category.MEAT));
                            Predicate p = root.get("id")
                                              .in(subQuery.getSelection())
                                              .not();
                            predicates.add(p);
                        }
                    });
            Optional.ofNullable(include)
                    .ifPresent(in -> {
                        if (!in.isEmpty()) {
                            Join<Recipe, Ingredient> join = root.join("ingredients");
                            predicates.add(join.get("name")
                                               .in(in));
                        }
                    });
            Optional.ofNullable(exclude)
                    .ifPresent(ex -> {
                        if (!ex.isEmpty()) {
                            Subquery<Long> subQuery = query.subquery(Long.class);
                            Join<Recipe, Ingredient> join = subQuery.from(Recipe.class)
                                                                    .join("ingredients");
                            subQuery.select(join.getParent()
                                                .get("id"));
                            subQuery.where(join.get("name")
                                               .in(ex));
                            Predicate p = root.get("id")
                                              .in(subQuery.getSelection())
                                              .not();
                            predicates.add(p);
                        }
                    });
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Transactional
    public Recipe createRecipe(Recipe newRecipe) {
        if (recipesRepository.existsByTitle(newRecipe.getTitle())) {
            throw new BadRequestException();
        }
        List<Ingredient> ingredients = newRecipe.getIngredients()
                                                .stream()
                                                .map(ingredient -> ingredientsRepository.findOneByName(ingredient.getName())
                                                                                        .orElse(ingredient))
                                                .toList();
        newRecipe.setIngredients(ingredients);
        return recipesRepository.save(newRecipe);
    }

    @Transactional
    public Recipe updateRecipe(Recipe updated) {
        List<Ingredient> ingredients = updated.getIngredients()
                                              .stream()
                                              .map(ingredient -> ingredientsRepository.findOneByName(ingredient.getName())
                                                                                      .orElse(ingredient))
                                              .collect(
                                                  Collectors.toCollection(ArrayList::new));
        return Optional.ofNullable(updated.getId())
                       .map(id -> recipesRepository.findById(id))
                       .orElseGet(() -> recipesRepository.findOneByTitle(updated.getTitle()))
                       .map(recipe -> {
                           recipe.setInstructions(updated.getInstructions());
                           recipe.setServings(updated.getServings());
                           recipe.setIngredients(ingredients);
                           recipe.setTitle(updated.getTitle());
                           return recipesRepository.save(recipe);
                       })
                       .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteRecipe(Long id) {
        recipesRepository.deleteById(id);
    }
}
