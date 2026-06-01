package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.CategoryListResponseDto;
import com.onlinelearning.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT new com.onlinelearning.model.dto.response.CategoryListResponseDto(c.categoryId, c.categoryName)\n"
            + "FROM Category c")
    List<CategoryListResponseDto> getAllCategories();
}
