package dev.abu.productservice3rdparty.exceptions;

public class CategoryNotFoundException extends  RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
