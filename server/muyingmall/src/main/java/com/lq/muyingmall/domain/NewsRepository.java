package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Transactional
    @Modifying
    @Query(value = "update news sm set sm.news=?1 where sm.id=?2 ", nativeQuery = true)
    void update(String news, int id);

}