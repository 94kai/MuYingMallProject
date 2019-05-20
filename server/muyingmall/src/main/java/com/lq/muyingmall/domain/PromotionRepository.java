package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    @Transactional
    void deleteAllByPomotionId(int promotionId);
    @Transactional
    Promotion findPromotionByPomotionId(int promotionId);

    @Transactional
    @Modifying
    @Query(value = "update Promotion sm set sm.title=?1 where sm.pomotion_id=?2 ", nativeQuery = true)
    void update(String title, int promotionId);

}