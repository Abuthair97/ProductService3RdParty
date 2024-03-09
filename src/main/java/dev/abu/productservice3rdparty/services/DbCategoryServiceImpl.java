package dev.abu.productservice3rdparty.services;

import dev.abu.productservice3rdparty.models.Category;
import dev.abu.productservice3rdparty.repositories.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbCategoryServiceImpl implements CategoryService{
   private ICategoryRepository iCategoryRepository;

   public DbCategoryServiceImpl(ICategoryRepository iCategoryRepository){
       this.iCategoryRepository = iCategoryRepository;
   }


}
