package dev.abu.productservice3rdparty.controllers;

import dev.abu.productservice3rdparty.dtos.CreateProductDto;
import dev.abu.productservice3rdparty.models.Product;
import dev.abu.productservice3rdparty.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
   private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}")
    Product getSingleProduct(@PathVariable  Long productId){
        return productService.getSingleProduct(productId);
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody CreateProductDto request ){
        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getImage(),
                request.getCategory(),
                request.getPrice()
        );
    }

    @GetMapping("products")
    List<Product> getAllProduct(){
     return productService.getAllProduct();
    }
    @GetMapping("products/category/{category}")
    List<Product> getBySpecificCategory(@PathVariable String category){
        return productService.getBySpecificCategory(category);
    }
    @GetMapping("products/category")
    List<String> getAllCategory(){
        return productService.getAllCategory();
    }

    @PutMapping("products/{id}")
    public Product updateProduct(@PathVariable  Long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id){
        System.out.println("Deleting Product with  "+id);
         productService.deleteProduct(id);
    }

}
