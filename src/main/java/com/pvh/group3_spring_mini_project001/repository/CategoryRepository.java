package com.pvh.group3_spring_mini_project001.repository;

import com.pvh.group3_spring_mini_project001.model.Category;
import com.pvh.group3_spring_mini_project001.model.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {

    @Select("""
            SELECT * FROM category_tb order by category_date desc limit #{pageSize} offset #{pageNo}
            """)
    @Results(id = "categoryMap", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_name"),
            @Result(property = "date", column = "category_date")
    })
    List<Category> getAllCategoryDesc(Integer pageNo,Integer pageSize);

    @Select("""
            SELECT * FROM category_tb where category_id=#{id}
            """)
    @ResultMap("categoryMap")
    Category searchCategoryById(Integer id);

    @Select("""
            insert into category_tb(category_name,user_id) values (#{cate.name},#{id}) returning *
            """)
    @ResultMap("categoryMap")
    Category addCategory(@Param("cate") CategoryRequest categoryRequest, Integer id);

    @Select("""
            select * from category_tb where user_id=#{id}
            """)
    @ResultMap("categoryMap")
    List<Category> getCategoryByUser(Integer id);


    @Select("""
            select * from category_tb where  category_id=#{id} and user_id=#{userId}
            """)
    @ResultMap("categoryMap")
    Category searchCategoryIdByUser(Integer id, Integer userId);

    @Delete("""
            delete from category_tb where category_id=#{id} and user_id=#{storeId}
            """)
    Integer deleteCategoryById(Integer id,Integer storeId);


    @Select("""
            update category_tb set category_name=#{cate.name} where category_id=#{id} and user_id=#{currentId} returning *
            """)
    @ResultMap("categoryMap")
    Category updateCategory(@Param("cate") CategoryRequest categoryRequest, Integer id,Integer currentId);
    @Select("""
            select * from category_tb order by category_date asc limit #{pageSize} offset #{pageNo}
            """)
    @ResultMap("categoryMap")
    List<Category> getAllCategoryAsc(Integer pageNo, Integer pageSize);
    @Select("""
            select * from category_tb where user_id=#{id} order by category_date desc limit #{pageSize} offset #{pageNo}
            """)
    @ResultMap("categoryMap")
    List<Category> getCategoryByUserDes(Integer id, Integer pageNo, Integer pageSize);
    @Select("""
              select * from category_tb where user_id=#{id} limit #{pageSize} offset #{pageNo}
            """)
    @ResultMap("categoryMap")
    List<Category> getCategoryByUserAsc(Integer id, Integer pageNo, Integer pageSize);
}
