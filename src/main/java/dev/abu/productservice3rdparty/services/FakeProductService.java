package dev.abu.productservice3rdparty.services;

import dev.abu.productservice3rdparty.dtos.FakeStoreProductDto;

import dev.abu.productservice3rdparty.models.Product;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto  =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+
                        productId,FakeStoreProductDto.class);


        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] response =
              restTemplate.getForObject("https://fakestoreapi.com/products",
                      FakeStoreProductDto[].class);

        List<Product> answer = new ArrayList<>();
        for (FakeStoreProductDto dto : response ){
         answer.add(dto.toProduct());
        }
        return answer;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
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

        return  responseUpdate.toProduct();
    }

    @Override
    public void deleteProduct(Long id) {
//        System.out.println("Deleting");
         restTemplate.delete("https://fakestoreapi.com/products/"+id);

    }

    @Override
    public List<Product> getBySpecificCategory(String category) {
        FakeStoreProductDto[] fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/category/"+category,
                        FakeStoreProductDto[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto dto: fakeStoreProductDto){
            answer.add(dto.toProduct());
        }
        return answer;
    }

    @Override
    public  List<String> getAllCategory() {
     List<String> ans =  restTemplate.getForObject(
             "https://fakestoreapi.com/products/categories",List.class);
     return ans;
    }


}

