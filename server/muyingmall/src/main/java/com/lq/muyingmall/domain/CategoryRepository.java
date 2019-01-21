package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //    Category findByUserName(String userName);
//    Category findByUserNameAndPassWord(String userName, String passWord);
    Category findByCategoryName(String categoryName);

    Category findCategoryByCategoryId(int categoryId);

    @Transactional
    void deleteAllByCategoryId(int categoryId);
}