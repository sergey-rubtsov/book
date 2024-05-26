package com.cook.book.repository;

import com.cook.book.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RecipesRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    boolean existsByTitle(String title);

    Optional<Recipe> findOneByTitle(String title);

}
