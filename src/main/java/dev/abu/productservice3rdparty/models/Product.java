package dev.abu.productservice3rdparty.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private Long id;
    private String title;
    private String description;
    private  String imageUrl;
    private Category category;
    private double price;
}
