package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Category findByUserName(String userName);
//    Category findByUserNameAndPassWord(String userName, String passWord);

}