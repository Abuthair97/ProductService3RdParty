package dev.abu.productservice3rdparty.services;

import dev.abu.productservice3rdparty.exceptions.CategoryNotFoundException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryService {
List<String> getAllCategory() throws  CategoryNotFoundException;
}
