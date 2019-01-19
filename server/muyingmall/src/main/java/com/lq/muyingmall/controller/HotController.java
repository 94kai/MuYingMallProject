package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.Banner;
import com.lq.muyingmall.domain.BannerRepository;
import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/api")
public class HotController {
    @Autowired
    private BannerRepository bannerRepository;

    //    http://img.muyingzhijia.com/img/201812/20181227093509_6_yingyangzuhe100.jpg
//    http://img.muyingzhijia.com/img/201711/20171101164953_10_20170810171638_10_1182.jpg
//    http://img.muyingzhijia.com/img/201802/20180208131608_10_1182.jpg
//    http://img.muyingzhijia.com/img/201710/20171017155554_10_20170927200414_10_20160214173251182.jpg
//    http://img.muyingzhijia.com/img/201707/20170714151010_10_tm0118.jpg
    @RequestMapping(value = "/queryHotBanner", method = {RequestMethod.GET})
    public BaseResponse<List<Banner>> findHotBanner() {
        //TODO: 最多请求5个
        Page<Banner> banners = bannerRepository.findAll(PageRequest.of(0, 5));

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
}
