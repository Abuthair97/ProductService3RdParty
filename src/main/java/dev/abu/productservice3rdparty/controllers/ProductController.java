package dev.abu.productservice3rdparty.controllers;

import dev.abu.productservice3rdparty.dtos.CreateProductDto;
import dev.abu.productservice3rdparty.exceptions.ProductNotFoundException;
import dev.abu.productservice3rdparty.models.Product;
import dev.abu.productservice3rdparty.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductController {
   private ProductService productService;
   private HttpHeaders httpHeaders;
    public ProductController(@Qualifier("DbProductService") ProductService productService, HttpHeaders httpHeaders) {
        this.productService = productService;
        this.httpHeaders=httpHeaders;
    }

    @GetMapping("/products/{productId}")
    ResponseEntity<Product> getSingleProduct(@PathVariable  Long productId) throws ProductNotFoundException {
        httpHeaders.add("Desc","Getting single Product with Id :"+productId);
        return new ResponseEntity<>(productService.getSingleProduct(productId), httpHeaders,HttpStatus.OK);
    }

    @PostMapping("/products")
    ResponseEntity<Product> createProduct(@RequestBody CreateProductDto request ){
        httpHeaders.add("Desc","New Product is added ");
        return new ResponseEntity<>( productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getImage(),
                request.getCategory(),
                request.getPrice()
        ),httpHeaders,HttpStatus.CREATED);
    }

    @GetMapping("products")
    ResponseEntity<List<Product>> getAllProduct() throws ProductNotFoundException {
        httpHeaders.add("Desc","Getting All Products ");
     return new ResponseEntity<>(productService.getAllProduct(),httpHeaders,HttpStatus.OK);
    }
    @GetMapping("products/category/{category}")
    ResponseEntity<List<Product>> getBySpecificCategory(@PathVariable String category) throws ProductNotFoundException {
        httpHeaders.add("Desc","Getting products from Specific category");
        return new ResponseEntity<>(productService.getBySpecificCategory(category),httpHeaders,HttpStatus.OK);
    }
    @GetMapping("products/category")
    ResponseEntity<List<String>> getAllCategory() throws ProductNotFoundException {
        httpHeaders.add("Desc","Getting List of All Category");
        return new ResponseEntity<>(productService.getAllCategory(),httpHeaders,HttpStatus.OK);
    }

    @PutMapping("products/{id}")
     ResponseEntity<Product> updateProduct(@PathVariable  Long id,@RequestBody Product product) throws ProductNotFoundException {
        httpHeaders.add("Desc","Updating Values of the product with Product Id : "+id);
        return new ResponseEntity<>( productService.updateProduct(id,product),httpHeaders,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("products/{id}")
     ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
//        System.out.println("Deleting Product with  "+id);
        httpHeaders.add("Desc","Deleting product with Id : "+id);
        productService.deleteProduct(id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(httpHeaders,HttpStatus.OK);
        return responseEntity;
    }


}
