package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Transactional
    void deleteAllByPomotionId(int promotionId);
    @Transactional
    Promotion findPromotionByPomotionId(int promotionId);
}