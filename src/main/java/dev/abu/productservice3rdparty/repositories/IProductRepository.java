package dev.abu.productservice3rdparty.repositories;

import dev.abu.productservice3rdparty.models.Category;
import dev.abu.productservice3rdparty.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product,Long> {
Product findByIdIs(Long id);

    @Query("SELECT p  FROM Product p INNER JOIN Category c ON p.category.id = c.id WHERE c.title = :categoryTitle ")
    List<Product> specificCategory( @Param("categoryTitle") String category);


}
