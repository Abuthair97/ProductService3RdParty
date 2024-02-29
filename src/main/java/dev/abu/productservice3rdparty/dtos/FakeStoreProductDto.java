package dev.abu.productservice3rdparty.dtos;

import dev.abu.productservice3rdparty.models.Category;
import dev.abu.productservice3rdparty.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private  String image;
    private String category;
    private double price;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        Category setCategory = new Category();
        setCategory.setTitle(category);
        product.setCategory(setCategory);
        product.setPrice(price);
        return product;
    }

}
