package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.Banner;
import com.lq.muyingmall.domain.BannerRepository;
import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.News;
import com.lq.muyingmall.domain.NewsRepository;
import com.lq.muyingmall.domain.Product;
import com.lq.muyingmall.domain.ProductRepository;
import com.lq.muyingmall.domain.Promotion;
import com.lq.muyingmall.domain.PromotionGroup;
import com.lq.muyingmall.domain.PromotionRepository;
import com.lq.muyingmall.utils.TextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class HotController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/queryHotBanner", method = {RequestMethod.GET})
    public BaseResponse<List<Banner>> findHotBanner() {
        //TODO: 最多请求5个
        Page<Banner> banners = bannerRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "id"));

        if (banners.isEmpty()) {
            return new BaseResponse<>(-1, "无数据");
        } else {
            ArrayList<Banner> data = new ArrayList<>();
            banners.get().forEach(data::add);
            return new BaseResponse<>(0, data);
        }
    }

    @RequestMapping(value = "/addHotBanner", method = {RequestMethod.POST})
    public BaseResponse addHotBanner(@RequestBody List<Banner> banners) {
        for (Banner banner : banners) {
            try {
                bannerRepository.deleteById(banner.getId());
                Product product = new Product();
                product.setId(banner.getId());
                product.setTitle(TextUtils.isEmpty(banner.getTitle())?"暂无标题":banner.getTitle());
                product.setImage(banner.getUrl());
                product.setSell_num(banner.getSell_num());
                try {
                    productRepository.deleteById(product.getId());
                } catch (EmptyResultDataAccessException e) {
                    logger.error("删除失败，并无大碍");
                }
                productRepository.save(product);
            } catch (Exception e) {
                logger.error("添加错误");
                e.printStackTrace();
            }
            bannerRepository.save(banner);
        }
        return new BaseResponse<>(0, "banner添加成功");
    }


    @RequestMapping(value = "/queryHotPromotion", method = {RequestMethod.GET})
    public BaseResponse<PromotionGroup> queryHotPromotion() {

        Page<Promotion> promotionsList = promotionRepository.findAll(PageRequest.of(0, 6, Sort.Direction.DESC, "id"));
        Page<News> newsList = newsRepository.findAll(PageRequest.of(0, 3, Sort.Direction.DESC, "id"));
        ArrayList<Promotion> promotionsListData;
        ArrayList<News> newsListData = new ArrayList<>();
        if (promotionsList.isEmpty()) {
            return new BaseResponse<>(-1, "暂无活动");
        } else {
            promotionsListData = new ArrayList<>();
            promotionsList.get().forEach(e -> {
                List<Product> products = productRepository.queryAllByPromotionId(e.getPomotionId());
                if (products.size() > 1) {
                    e.setProductsList(products);
                    promotionsListData.add(e);
                }
            });
        }

        if (newsList.isEmpty()) {
            News news = new News();
            news.setNews("暂无新闻");
            newsListData.add(news);
        } else {
            newsList.get().forEach(newsListData::add);
        }
        PromotionGroup promotionGroup = new PromotionGroup();
        promotionGroup.setNewsList(newsListData);
        promotionGroup.setPromotionList(promotionsListData);
        return new BaseResponse<>(0, promotionGroup);


    }


    @RequestMapping(value = "/addHotPromotion", method = {RequestMethod.POST})
    public BaseResponse addHotPromotion(@RequestBody List<Promotion> promotions) {
        try {
            for (Promotion promotion : promotions) {
                promotionRepository.deleteAllByPomotionId(promotion.getPomotionId());
                promotionRepository.save(promotion);
            }
            return new BaseResponse(0);
        } catch (Exception e) {
            return new BaseResponse(-1, "添加失败");
        }
    }

    @RequestMapping(value = "/addHotNews", method = {RequestMethod.POST})
    public BaseResponse addHotNews(@RequestBody List<News> newsList) {
        if (newsList != null && newsList.size() > 0) {
            for (News news : newsList) {
                newsRepository.save(news);
            }
            return new BaseResponse(0);
        }
        return new BaseResponse(-1, "news不能为空");
    }
}
