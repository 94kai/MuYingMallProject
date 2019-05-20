package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //    Category findByUserName(String userName);
//    Category findByUserNameAndPassWord(String userName, String passWord);
    Category findByCategoryName(String categoryName);

    Category findCategoryByCategoryId(int categoryId);

    @Transactional
    void deleteAllByCategoryId(int categoryId);


    @Transactional
    @Modifying
    @Query(value = "update category sm set sm.category_name =?1 where sm.category_id=?2 ", nativeQuery = true)
    void update(String name, int id);

}