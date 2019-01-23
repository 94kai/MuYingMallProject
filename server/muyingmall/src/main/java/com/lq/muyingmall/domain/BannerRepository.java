package com.lq.muyingmall.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    //    Category findByUserName(String userName);
//    Category findByUserNameAndPassWord(String userName, String passWord);
    Page<Banner> findAll(Pageable pageable);
}