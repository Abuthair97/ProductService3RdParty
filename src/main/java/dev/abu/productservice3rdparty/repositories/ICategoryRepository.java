package dev.abu.productservice3rdparty.repositories;

import dev.abu.productservice3rdparty.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category,Long> {


    Category findByTitle(String category);
}
