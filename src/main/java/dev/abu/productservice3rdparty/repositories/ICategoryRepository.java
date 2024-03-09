package dev.abu.productservice3rdparty.repositories;

import dev.abu.productservice3rdparty.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category,Long> {


    @Query("select c.title from Category c ")
    List<String> allCategory();

    Category findByTitle(String category);
}
