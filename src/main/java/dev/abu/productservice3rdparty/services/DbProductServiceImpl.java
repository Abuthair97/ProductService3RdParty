package dev.abu.productservice3rdparty.services;

import dev.abu.productservice3rdparty.exceptions.ProductNotFoundException;
import dev.abu.productservice3rdparty.models.Category;
import dev.abu.productservice3rdparty.models.Product;
import dev.abu.productservice3rdparty.repositories.ICategoryRepository;
import dev.abu.productservice3rdparty.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DbProductService")
public class DbProductServiceImpl implements ProductService{

    private IProductRepository iProductRepository;
    private ICategoryRepository iCategoryRepository;
    public DbProductServiceImpl(IProductRepository iProductRepository,ICategoryRepository iCategoryRepository){
        this.iProductRepository = iProductRepository;
        this.iCategoryRepository=iCategoryRepository;
    }
    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category categoryFromDatabase = iCategoryRepository.findByTitle(category);


        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);

            categoryFromDatabase = newCategory;

        }

        // if the category was found from DB -> category1 will be having an ID
        // else: category1 won't have any ID
        product.setCategory(categoryFromDatabase);

        Product savedProduct = iProductRepository.save(product);

        return savedProduct;

    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException{
        return  iProductRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("There is no Product with Id : " + productId));
    }


    @Override
    public List<Product> getAllProduct() throws ProductNotFoundException {
        List<Product> getAll = iProductRepository.findAll();
        if(getAll.isEmpty()){
            throw new ProductNotFoundException("No Product found ");
        }
        return getAll;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
       Product product1 =  iProductRepository
               .findById(id)
               .orElseThrow( () -> new ProductNotFoundException ("There is no Product with Id : " + id) );
       product1.setTitle(product.getTitle());
       product1.setDescription(product.getDescription());
       product1.setCategory(product.getCategory());
       product1.setImageUrl(product.getImageUrl());
       product1.setPrice(product.getPrice());

        return iProductRepository.save(product1);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
       if(!iProductRepository.existsById(id)){
           throw new ProductNotFoundException("There is no product with Id : " +id);
       }
        iProductRepository.deleteById(id);
    }

    @Override
    public List<Product> getBySpecificCategory(String category) throws ProductNotFoundException {

        return  null;
    }

    @Override
    public List<String> getAllCategory() throws ProductNotFoundException {
        return null;
    }
}
