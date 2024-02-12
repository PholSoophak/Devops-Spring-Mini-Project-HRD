package com.pvh.group3_spring_mini_project001.service;

import com.pvh.group3_spring_mini_project001.model.Category;
import com.pvh.group3_spring_mini_project001.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory(boolean asc, boolean des, Integer pageNo, Integer pageSize);
    Category searchCategoryById(Integer id);
    Category addCategory(CategoryRequest categoryRequest,Integer id);
    List<Category>getCategoryByUser(Integer id ,boolean asc, boolean des, Integer pageNo, Integer pageSize);

    Category searchCategoryIdByUser(Integer id,Integer userId);
     void  deleteCategoryById(Integer id,Integer storeId);

    Category updateCategory(CategoryRequest categoryRequest,Integer id,Integer userId);
}

