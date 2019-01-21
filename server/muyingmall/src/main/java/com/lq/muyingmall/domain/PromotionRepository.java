package com.lq.muyingmall.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Transactional
    void deleteAllByPomotionId(int promotionId);
}