package dev.abu.productservice3rdparty.repositories;

import dev.abu.productservice3rdparty.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product,Long> {
Product findByIdIs(Long id);

}
