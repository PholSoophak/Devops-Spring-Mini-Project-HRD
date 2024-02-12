package com.pvh.group3_spring_mini_project001.controller;

import com.pvh.group3_spring_mini_project001.constance.Auth;
import com.pvh.group3_spring_mini_project001.model.Category;
import com.pvh.group3_spring_mini_project001.model.User;
import com.pvh.group3_spring_mini_project001.model.request.CategoryRequest;
import com.pvh.group3_spring_mini_project001.response.Response;
import com.pvh.group3_spring_mini_project001.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/api/")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping("categories")
    public ResponseEntity<Response<List<Category>>> getAllCategory(@RequestParam(defaultValue = "false") boolean asc,@RequestParam(defaultValue = "false") boolean des
            ,@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "3")Integer pageSize) {
        Response<List<Category>> response = Response.<List<Category>>builder()
                .payload(categoryService.getAllCategory(asc,des,pageNo,pageSize))
                .dateTime(LocalDateTime.now())
                .status(true)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<Response<Category>> searchCategoryById(
            @PathVariable int id
    ) {
        if (categoryService.searchCategoryById(id) != null) {
            Response<Category> response = Response.<Category>builder()
                    .payload(categoryService.searchCategoryById(id))
                    .dateTime(LocalDateTime.now())
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("categories/users")
    public ResponseEntity<Response<Category>> addCategory(
            @RequestBody CategoryRequest categoryRequest
            ) {
            Response<Category> response = Response.<Category>builder()
                    .payload(categoryService.addCategory(categoryRequest,Auth.USER_ID))
                    .dateTime(LocalDateTime.now())
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);
    }

    @GetMapping("categories/users")
    public ResponseEntity<Response<List<Category>>> getCategoryByUser(@RequestParam(defaultValue = "false") boolean asc,@RequestParam(defaultValue = "false") boolean des
            ,@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "3")Integer pageSize) {
            Response<List<Category>> response = Response.<List<Category>>builder()
                    .payload(categoryService.getCategoryByUser(Auth.USER_ID,asc,des,pageNo,pageSize))
                    .dateTime(LocalDateTime.now())
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);
    }
    @GetMapping("categories/{id}/users")
    public ResponseEntity<Response<Category>> searchCategoryIdByUser(
            @PathVariable Integer id
    ) {
            Response<Category> response = Response.<Category>builder()
                    .payload(categoryService.searchCategoryIdByUser(id,Auth.USER_ID))
                    .dateTime(LocalDateTime.now())
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("categories/{id}/users")
    public ResponseEntity<Response<Category>> deleteCategoryById(
            @PathVariable Integer id
    ) {
            categoryService.deleteCategoryById(id,Auth.USER_ID);
            Response<Category> response = Response.<Category>builder()
                    .payload(null)
                    .dateTime(LocalDateTime.now())
                    .message("Delete category from user Id: " +Auth.USER_ID+" Successfully")
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);

    }

    @PutMapping("categories/{id}/users")
    public ResponseEntity<Response<Category>> updateCategory(
            @RequestBody CategoryRequest categoryRequest,
            @PathVariable Integer id
    ) {
            Response<Category> response = Response.<Category>builder()
                    .payload(categoryService.updateCategory(categoryRequest,id,Auth.USER_ID))
                    .dateTime(LocalDateTime.now())
                    .status(true)
                    .build();
            return ResponseEntity.ok().body(response);

    }

}
