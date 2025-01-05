package com.example.ex1curs9.repository;

import com.example.ex1curs9.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    boolean existsByName(String name);

    List<Category> findAllByOrderByNameAsc(); // listraea categoriilor în ordine alfabetică


    @Query("SELECT c FROM Category c WHERE SIZE(c.books) > 0")
    List<Category> findNonEmptyCategories(); // lista categoriilor care au cărți

    // lista cartilor dintr o categorie
    @Query("SELECT c.books FROM Category c WHERE c.id = :categoryId")
    List<Category> findBooksByCategoryId(@Param("categoryId") Long categoryId);

}