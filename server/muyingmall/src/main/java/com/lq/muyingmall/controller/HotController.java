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
            return new BaseResponse<>(-1, "无数据", null);
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
                product.setTitle(TextUtils.isEmpty(banner.getTitle()) ? "暂无标题" : banner.getTitle());
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

    @RequestMapping(value = "/updatePromotion", method = {RequestMethod.GET})
    public BaseResponse updatePromotion(int promotionId,String title) {
        logger.error("updatePromotion");
        try {
            promotionRepository.update(title,promotionId);
        } catch (Exception e) {
            return new BaseResponse(-1, "失败");
        }
        return new BaseResponse(0, "updatePromotion成功");
    }

    @RequestMapping(value = "/updateNews", method = {RequestMethod.GET})
    public BaseResponse updateNews(int id,String news) {
        logger.error("updateNews");
        try {
            newsRepository.update(news,id);
        } catch (Exception e) {
            return new BaseResponse(-1, "updateNews失败");
        }
        return new BaseResponse(0, "updateNews成功");
    }

    @RequestMapping(value = "/addPromotion", method = {RequestMethod.GET})
    public BaseResponse addPromotion(String title) {
        try {
            Promotion promotion = new Promotion();
            promotion.setTitle(title);
            promotionRepository.save(promotion);
        } catch (Exception e) {
            return new BaseResponse<>(-1, "addPromotion失败");
        }
        return new BaseResponse<>(0, "addPromotion成功");
    }

    @RequestMapping(value = "/addNews", method = {RequestMethod.GET})
    public BaseResponse addNews(String news) {
        try {
            News news1 = new News();
            news1.setNews(news);
            newsRepository.save(news1);
        } catch (Exception e) {
            return new BaseResponse<>(-1, "addNews失败");
        }
        return new BaseResponse<>(0, "addNews成功");
    }

    @RequestMapping(value = "/queryAllHotPromotion", method = {RequestMethod.GET})
    public BaseResponse<PromotionGroup> queryAllHotPromotion() {
        List<News> newsListData;
        List<Promotion> promotionsListData;

        promotionsListData = promotionRepository.findAll(Sort.by(Sort.Direction.DESC, "pomotionId"));
        newsListData = newsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (newsListData.size() == 0) {
            News news = new News();
            news.setNews("暂无新闻");
            newsListData.add(news);
        }

        if (newsListData.size() == 0) {
            News news = new News();
            news.setNews("暂无新闻");
            newsListData.add(news);
        }

        if (promotionsListData.isEmpty()) {
            return new BaseResponse<>(-1, "暂无活动", null);
        } else {
            promotionsListData.forEach(promotion -> {
                List<Product> products = productRepository.queryAllByPromotionId(promotion.getPomotionId());
                if (products.size() > 1) {
                    products.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
                    promotion.setProductsList(products);
                }
            });
        }
        PromotionGroup promotionGroup = new PromotionGroup();
        promotionGroup.setNewsList(newsListData);
        promotionGroup.setPromotionList(promotionsListData);
        return new BaseResponse<>(0, promotionGroup);
    }

    @RequestMapping(value = "/queryHotPromotion", method = {RequestMethod.GET})
    public BaseResponse<PromotionGroup> queryHotPromotion() {

        Page<Promotion> promotionsList = promotionRepository.findAll(PageRequest.of(0, 6, Sort.Direction.DESC, "pomotionId"));
        Page<News> newsList = newsRepository.findAll(PageRequest.of(0, 3, Sort.Direction.DESC, "id"));
        ArrayList<Promotion> promotionsListData;
        ArrayList<News> newsListData = new ArrayList<>();
        if (promotionsList.isEmpty()) {
            promotionsListData = new ArrayList<>();
        } else {
            promotionsListData = new ArrayList<>();
            promotionsList.get().forEach(e -> {
                List<Product> products = productRepository.queryAllByPromotionId(e.getPomotionId());
                if (products.size() > 1) {
                    products.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
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
            promotionRepository.saveAll(promotions);
            return new BaseResponse(0);
        } catch (Exception e) {
            return new BaseResponse(-1, "添加失败");
        }
    }

    @RequestMapping(value = "/deletePromotion", method = {RequestMethod.GET})
    public BaseResponse deletePromotion(int promotionId) {
        try {
            promotionRepository.deleteById(promotionId);
            return new BaseResponse(0);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(-1, "删除失败");
        }
    }

    @RequestMapping(value = "/deleteNews", method = {RequestMethod.GET})
    public BaseResponse deleteNews(long id) {
        try {
            newsRepository.deleteById(id);
            return new BaseResponse(0,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(-1, "删除失败");
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
