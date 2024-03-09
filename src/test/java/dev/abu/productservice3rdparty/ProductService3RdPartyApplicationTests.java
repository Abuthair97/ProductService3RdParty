package dev.abu.productservice3rdparty;

import dev.abu.productservice3rdparty.repositories.ICategoryRepository;
import dev.abu.productservice3rdparty.repositories.IProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductService3RdPartyApplicationTests {

    @Autowired
   private IProductRepository iProductRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Test
    void contextLoads() {
    }

    @Test
    void findByIdIs(Long Id){
        iProductRepository.findByIdIs(Id);
    }
  @Test
    void findAllCategory(){
        iCategoryRepository.findAll();
  }

  @Test
    void findAllCategoryString(String category){
        iCategoryRepository.findByTitle(category);
  }
}
