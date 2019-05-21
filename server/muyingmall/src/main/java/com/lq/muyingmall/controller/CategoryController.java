package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.Category;
import com.lq.muyingmall.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/queryAllCategory", method = {RequestMethod.GET})
    public BaseResponse<List<Category>> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryId"));
        return new BaseResponse<>(0, categoryList);
    }

    /**
     * 添加分类
     */
    @RequestMapping(value = "/addCategory", method = {RequestMethod.POST})
    public BaseResponse addCategory(@RequestBody List<Category> categorys) {
        for (Category category : categorys) {
            categoryRepository.deleteAllByCategoryId(category.getCategoryId());
            categoryRepository.save(category);
        }
        return new BaseResponse(0, "添加成功");
    }


    @RequestMapping(value = "/updateCategory", method = {RequestMethod.GET})
    public BaseResponse updateCategory(int id, String name) {
        try {
            categoryRepository.update(name, id);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(-1, "updateCategory失败");
        }
        return new BaseResponse(0, "updateCategory成功");
    }

    @RequestMapping(value = "/addCategory", method = {RequestMethod.GET})
    public BaseResponse addCategory(String name) {
        try {
            Category category = new Category();
            category.setCategoryName(name);
            categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>(-1, "addCategory失败");
        }
        return new BaseResponse<>(0, "addCategory成功");
    }

    @RequestMapping(value = "/deleteCategory", method = {RequestMethod.GET})
    public BaseResponse deleteCategory(int id) {
        try {
            categoryRepository.deleteById(id);
            return new BaseResponse(0);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(-1, "删除失败");
        }
    }
}
