package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.Category;
import com.lq.muyingmall.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        List<Category> categoryList = categoryRepository.findAll();
        return new BaseResponse<>(0, categoryList);
    }

    /**
     * 添加分类
     *
     * @param passWord 超级管理员密码
     */
    @RequestMapping(value = "/addCategory", method = {RequestMethod.POST})
    public BaseResponse addCategory(@RequestBody List<Category> categorys,
                                    @RequestHeader(value = "password", required = false) String passWord) {
        //TODO:校验密码
        if ("123".equals(passWord)) {
            for (Category category : categorys) {
                categoryRepository.deleteAllByCategoryId(category.getCategoryId());
                categoryRepository.save(category);
            }
            return new BaseResponse(0, "添加成功");
        } else {
            return new BaseResponse(-1, "无权限");
        }
    }


//    @RequestMapping(value = "/addCategory", method = {RequestMethod.POST})
//    public String addCategory(String context, @RequestHeader(value = "token", required = false) String token) {
//        logger.info("hhhhh=======" + context);
//        return "hhhhh";
//    }
//
//    @RequestMapping(value = "/register", method = {RequestMethod.POST})
//    public BaseResponse register(@RequestBody User user) {
//        User tempUser = userRepository.findByUserName(user.getUserName());
//        if (tempUser != null) {
//            return new BaseResponse(-1, "用户名被占用", "");
//        }
//        User save = userRepository.save(user);
//        String token = getTokenByUserName(save.getUserName());
//        tokenMap.put(save.getUserName(), token);
//        return new BaseResponse(0, "注册成功", token);
//    }
//
//
//    /**
//     * 通过username获取token
//     */
//    private String getTokenByUserName(String userName) {
//        return DigestUtils.md5DigestAsHex((userName + System.currentTimeMillis()).getBytes());
//    }
}
