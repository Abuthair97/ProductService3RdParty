package dev.abu.productservice3rdparty.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
   private  String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
