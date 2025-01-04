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
    List<Category> findByDescriptionContaining(String description);

    List<Category> findByBooksIsNotEmpty();

    List<Category> findAllByOrderByNameAsc();

    @Query("SELECT c FROM Category c WHERE SIZE(c.books) >= :minBooks")
    List<Category> findCategoriesWithMinBooks(@Param("minBooks") int minBooks);
}