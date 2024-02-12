package com.pvh.group3_spring_mini_project001.service.serviceImp;

import com.pvh.group3_spring_mini_project001.exception.EmptyValidate;
import com.pvh.group3_spring_mini_project001.exception.NotFoundValidate;
import com.pvh.group3_spring_mini_project001.model.Category;
import com.pvh.group3_spring_mini_project001.model.request.CategoryRequest;
import com.pvh.group3_spring_mini_project001.repository.CategoryRepository;
import com.pvh.group3_spring_mini_project001.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory(boolean asc, boolean des, Integer pageNo, Integer pageSize) {
        pageNo=(pageNo-1)*pageSize;
        if(des){
          return  categoryRepository.getAllCategoryDesc(pageNo,pageSize);
        }
        return  categoryRepository.getAllCategoryAsc(pageNo,pageSize);
    }

    //search
    @Override
    public Category searchCategoryById(Integer id) {
        Category category = categoryRepository.searchCategoryById(id);
        if (category == null) {
            throw new NotFoundValidate("Not found Category");
        }
        return category;
    }
    //add new
    @Override
    public Category addCategory(CategoryRequest categoryRequest, Integer id) {
        if (categoryRequest.getName().isEmpty()) {
            throw new EmptyValidate("Category Name cannot empty !");
        } else if (categoryRequest.getName().isBlank()) {
            throw new EmptyValidate("Category Name cannot null please Input your Name !");
        } else {
            return categoryRepository.addCategory(categoryRequest, id);
        }
    }

    @Override
    public List<Category> getCategoryByUser(Integer id,boolean asc, boolean des, Integer pageNo, Integer pageSize) {
        pageNo=(pageNo-1)*pageSize;
        List<Category> categories;
        if (des){
            categories =categoryRepository.getCategoryByUserDes(id,pageNo,pageSize);
        }else {
            categories=categoryRepository.getCategoryByUserAsc(id,pageNo,pageSize);
        }

        if (categories.isEmpty()){
            throw new NotFoundValidate("You don' have any category");
        }
        return categories;
    }

    @Override
    public Category searchCategoryIdByUser(Integer id, Integer userId) {
        searchCategoryById(id);
        Category storeCat = categoryRepository.searchCategoryIdByUser(id, userId);
        if (storeCat == null){
            throw new NotFoundValidate("You dont have this Category");
        }
        return storeCat;
    }

    @Override
    public void deleteCategoryById(Integer id, Integer storeId) {
        searchCategoryById(id);
        if
        (categoryRepository.deleteCategoryById(id, storeId) != 1) {
            throw new NotFoundValidate("Cannot delete category  other user");
        }
    }
    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Integer id, Integer storeId) {
        searchCategoryById(id);
        Category category=categoryRepository.updateCategory(categoryRequest, id,storeId);
        if
        (category == null) {
            throw new NotFoundValidate("Cannot Update category other user");
        }
        return category;
    }
}
