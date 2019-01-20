package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.jsf.FacesContextUtils;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/api")
public class HotController {
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private NewsRepository newsRepository;

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

    @RequestMapping(value = "/addHotBanner", method = {RequestMethod.GET})
    public BaseResponse addHotBanner(String bannerImg) {
        Banner banner = bannerRepository.findByBannerImg(bannerImg);
        if (banner != null) {
            return new BaseResponse<>(-1, "已经添加过");
        } else {
            bannerRepository.save(new Banner(bannerImg));
            return new BaseResponse<>(0, "banner添加成功");
        }
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
            promotionsList.get().forEach(promotionsListData::add);
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
    public BaseResponse addHotPromotion(@RequestBody Promotion promotion) {
        if ((promotion.getTitle() != null && promotion.getTitle().length() > 0)) {
            if (promotion.getProduct() != null && promotion.getProduct().size() > 1) {
                promotionRepository.save(promotion);
                return new BaseResponse(0);
            } else {
                return new BaseResponse(-1, "活动数应该为2");
            }
        } else {
            return new BaseResponse(-1, "title不能为空");
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
