package com.cook.book.repository;

import com.cook.book.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findOneByName(String name);

    boolean existsByName(String name);

}
