package dev.abu.productservice3rdparty.services;


import dev.abu.productservice3rdparty.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);
    Product getSingleProduct(Long productId);
    List<Product> getAllProduct();

    Product updateProduct(Long id ,Product product);
    void deleteProduct(Long id);
    List<Product> getBySpecificCategory(String category);

    List<String> getAllCategory();
}
