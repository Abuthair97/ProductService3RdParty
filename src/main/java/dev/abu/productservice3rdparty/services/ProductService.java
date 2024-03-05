package dev.abu.productservice3rdparty.services;


import dev.abu.productservice3rdparty.exceptions.ProductNotFoundException;
import dev.abu.productservice3rdparty.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProduct() throws ProductNotFoundException;

    Product updateProduct(Long id ,Product product) throws ProductNotFoundException;
    void deleteProduct(Long id) throws ProductNotFoundException;
    List<Product> getBySpecificCategory(String category) throws ProductNotFoundException;

    List<String> getAllCategory() throws ProductNotFoundException;
}
