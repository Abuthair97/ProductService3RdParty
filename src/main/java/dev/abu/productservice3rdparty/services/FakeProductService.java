package dev.abu.productservice3rdparty.services;

import dev.abu.productservice3rdparty.dtos.FakeStoreProductDto;

import dev.abu.productservice3rdparty.exceptions.ProductNotFoundException;
import dev.abu.productservice3rdparty.models.Product;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreService")
public class FakeProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto  = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto,FakeStoreProductDto.class);

        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto  =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+
                        productId,FakeStoreProductDto.class);
          if(fakeStoreProductDto == null){
              throw new ProductNotFoundException("Product with id: " + productId + " doesn't exist. Retry some other product.");
          }

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProduct() throws ProductNotFoundException {
        FakeStoreProductDto[] response =
              restTemplate.getForObject("https://fakestoreapi.com/products",
                      FakeStoreProductDto[].class);

        List<Product> answer = new ArrayList<>();
        for (FakeStoreProductDto dto : response ){
         answer.add(dto.toProduct());
        }
        if(answer.isEmpty()){
            throw new ProductNotFoundException("No Products Present");
        }
        return answer;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        FakeStoreProductDto responseUpdate = new FakeStoreProductDto();
        responseUpdate.setId(product.getId());
        responseUpdate.setTitle(product.getTitle());
        responseUpdate.setDescription(product.getDescription());
        responseUpdate.setImage(product.getImageUrl());
        responseUpdate.setPrice(product.getPrice());
        responseUpdate.setCategory(product.getCategory().getTitle());

        restTemplate.put(
                "https://fakestoreapi.com/products/" + id,
                responseUpdate);

        if(responseUpdate.getId() == null){
            throw new ProductNotFoundException("Product with Id : "+id +" doesn't exist so product can't be updated");
        }

        return  responseUpdate.toProduct();
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
//        System.out.println("Deleting");
         restTemplate.delete("https://fakestoreapi.com/products/"+id);
         Product product = new Product();
        if( product.getId() == null){
            throw new ProductNotFoundException("Product with Id : "+id +" doesn't exist so product can't be Deleted");
        }
    }

    @Override
    public List<Product> getBySpecificCategory(String category) throws ProductNotFoundException {
        FakeStoreProductDto[] fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/category/"+category,
                        FakeStoreProductDto[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto dto: fakeStoreProductDto){
            answer.add(dto.toProduct());
        }
        if(answer.isEmpty()){
            throw new ProductNotFoundException("No Product with category : "+category +" is present ");
        }
        return answer;
    }

    @Override
    public  List<String> getAllCategory() throws ProductNotFoundException {
     List<String> ans =  restTemplate.getForObject(
             "https://fakestoreapi.com/products/categories",List.class);

     if(ans == null){
         throw new ProductNotFoundException("NO Category is Present");
     }
     return ans;
    }


}

