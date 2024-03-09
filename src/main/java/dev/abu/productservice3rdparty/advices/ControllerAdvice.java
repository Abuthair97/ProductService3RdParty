package dev.abu.productservice3rdparty.advices;

import dev.abu.productservice3rdparty.dtos.ErrorDto;
import dev.abu.productservice3rdparty.exceptions.CategoryNotFoundException;
import dev.abu.productservice3rdparty.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {
        ErrorDto dto = new ErrorDto();
        dto.setMessage(exception.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException){
        ErrorDto dto = new ErrorDto();
        dto.setMessage(categoryNotFoundException.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }
}