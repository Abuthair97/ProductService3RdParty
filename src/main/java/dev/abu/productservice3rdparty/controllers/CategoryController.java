package dev.abu.productservice3rdparty.controllers;

import dev.abu.productservice3rdparty.services.CategoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    private HttpHeaders httpHeaders;

    public CategoryController(CategoryService categoryService,HttpHeaders httpHeaders){
        this.categoryService=categoryService;
        this.httpHeaders = httpHeaders;
    }

    @GetMapping("/products/category")
    ResponseEntity<List<String>> getAllCategory() {
        httpHeaders.add("Desc","Getting List of Categories ");
        return new ResponseEntity<>( categoryService.getAllCategory(), httpHeaders,HttpStatus.OK);
    }

}
