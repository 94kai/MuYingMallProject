package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.CategoryRepository;
import com.lq.muyingmall.domain.Product;
import com.lq.muyingmall.domain.ProductDetail;
import com.lq.muyingmall.domain.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping(value = "/api")
public class ProductDetailController {

    String[] goodsTag = {
            "包邮",
            "运费险",
    };
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    //            "data": {
//        "title": "\u6f4d\u574a\u98ce\u7b5d\u8001\u9e70\u91d1\u9c7c\u8774\u8776\u6c99\u71d5\u8759\u8760\u5361\u901a\u98ce\u7b5d",
//                "id": 18174928,
//                "details": "\u6b63\u5b97\u6f4d\u574a\u5236\u98ce\u7b5d\uff0c\u9aa8\u67b6\u5f88\u7262\u56fa\uff0c\u5fae\u98ce\u5c31\u80fd\u653e\u8d77\u6765\uff0c\u51fa\u95e8\u8e0f\u9752\u6625\u6e38\u5fc5\u4e0d\u53ef\u5c11\u54e6\uff0c\u5168\u5bb6\u90fd\u80fd\u53c2\u4e0e\uff0c\u4eb2\u5b50\u6d3b\u52a8\u9996\u9009\uff01",
//                "pic_list": ["https:\/\/img.alicdn.com\/imgextra\/i1\/2270433976\/TB2Ibx1mwxlpuFjSszgXXcJdpXa_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i1\/2270433976\/O1CN01g1GgbE1fF3FafsbAm_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i1\/2270433976\/O1CN012kVnmu1fF3FajVDUa_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i4\/2270433976\/O1CN01jiJ1Wx1fF3FbLtYIh_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i2\/2270433976\/O1CN01bIZgsd1fF3FbsFTPp_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i2\/2270433976\/O1CN01WL9a9W1fF3FaJfGIP_!!2270433976.jpg_600x600.jpg_.webp"],
//        "original_price": 19.9,
//                "sell_num": 4857,
//                "goods_tag": ["\u5305\u90ae", "\u8fd0\u8d39\u9669"],
//        "store": {
//            "dsr": 4.8,
//                    "service": 4.8,
//                    "delivery": 4.8,
//                    "shop_name": "\u6c38\u5065\u6237\u5916\u65d7\u8230\u5e97",
//                    "shop_logo": "https:\/\/img.alicdn.com\/imgextra\/\/fa\/2e\/TB1fLetLpXXXXcWaXXXSutbFXXX.jpg",
//        },
//        "recommend": [ {
//            "title": "\u6f4d\u574a\u65b0\u6b3e\u5927\u578b\u7acb\u4f53\u7ffc\u9f99\u98ce\u7b5d",
//                    "original_price": 44.99,
//                    "image": "https:\/\/img.alicdn.com\/imgextra\/i4\/772971083\/O1CN01WqdvvR1Js3QpyUvEZ_!!772971083.jpg_310x310.jpg_.webp",
//                    "id": 18153547,
//        }, {
//            "title": "\u5927\u578b\u8774\u8776\u6c99\u71d5\u9e70\u5fae\u98ce\u6613\u98de\u98ce\u7b5d",
//                    "original_price": 19.9,
//                    "image": "https:\/\/img.alicdn.com\/bao\/uploaded\/TB25b0Bl4PI8KJjSspfXXcCFXXa_!!3592695041.png",
//                    "id": 18195587,
//        }],
//    };
    @RequestMapping(value = "/queryProductDetailById", method = {RequestMethod.GET})
    public BaseResponse<ProductDetail> queryProductDetailById(String productId) {
        Random random = new Random();

        Optional<Product> productOptional = productRepository.findById((long) Integer.parseInt(productId));
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            ProductDetail productDetail = new ProductDetail();

            productDetail.setTitle(product.getTitle());
            productDetail.setId(product.getId());
            productDetail.setOriginal_price(product.getOriginal_price());
            productDetail.setSell_num(product.getSell_num());
            List<String> picList = new ArrayList<>();
            picList.add(product.getImage());
            // TODO: by xk 2019/1/26 下午6:55 添加多个banner图片
//            picList.add(product.getImage());
            productDetail.setPic_list(picList);
            List<String> goodsTag = new ArrayList<>();
            goodsTag.add("包邮");
            goodsTag.add("运费险");
            productDetail.setGoods_tag(goodsTag);
            ProductDetail.Store store = new ProductDetail.Store();
            store.setDelivery(4.8f);
            store.setDsr(4.8f);
            store.setService(4.9f);
            store.setShop_name("LQ母婴旗舰店");
            store.setShop_logo("http://img2.3png.com/6eb7d08b17b675764e4e60506724ee1f1f89.png");
            productDetail.setStore(store);
            productDetail.setDetails("欢迎光临" + store.getShop_name() + "，该商品为" + productDetail.getTitle());


            List<Product> products = productRepository.queryAllByCategoryId(product.getCategoryId());
            int recommendsCount = 10;
            if (products.size()>0) {
                if (products.size() < recommendsCount) {
                    productDetail.setRecommend(products);
                } else {
                    List<Product> recommends = new ArrayList<>();
                    //生成recommendsCount个随机数
                    HashSet<Integer> integers = new HashSet<>();
                    while (integers.size() < recommendsCount) {
                        int i = random.nextInt(recommendsCount);
                        integers.add(i);
                    }
                    for (Integer integer : integers) {
                        Product product1 = products.get(integer);
                        recommends.add(product1);
                    }
                    productDetail.setRecommend(recommends);
                }
            }
            return new BaseResponse<>(0, productDetail);
        } else {
            return new BaseResponse<>(-1, "不存在");
        }

//        BaseResponse<String> response = new BaseResponse<>();
//        response.setData("{\n" +
//                "\t\"status\": 0,\n" +
//                "\t\"data\": {\n" +
//                "\t\t\"overdue\": 1,\n" +
//                "\t\t\"title\": \"\\u6f4d\\u574a\\u98ce\\u7b5d\\u8001\\u9e70\\u91d1\\u9c7c\\u8774\\u8776\\u6c99\\u71d5\\u8759\\u8760\\u5361\\u901a\\u98ce\\u7b5d\",\n" +
//                "\t\t\"id\": 18174928,\n" +
//                "\t\t\"details\": \"\\u6b63\\u5b97\\u6f4d\\u574a\\u5236\\u98ce\\u7b5d\\uff0c\\u9aa8\\u67b6\\u5f88\\u7262\\u56fa\\uff0c\\u5fae\\u98ce\\u5c31\\u80fd\\u653e\\u8d77\\u6765\\uff0c\\u51fa\\u95e8\\u8e0f\\u9752\\u6625\\u6e38\\u5fc5\\u4e0d\\u53ef\\u5c11\\u54e6\\uff0c\\u5168\\u5bb6\\u90fd\\u80fd\\u53c2\\u4e0e\\uff0c\\u4eb2\\u5b50\\u6d3b\\u52a8\\u9996\\u9009\\uff01\",\n" +
//                "\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/2270433976\\/TB2Ibx1mwxlpuFjSszgXXcJdpXa_!!2270433976.jpg\",\n" +
//                "\t\t\"pic_list\": [\"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/2270433976\\/TB2Ibx1mwxlpuFjSszgXXcJdpXa_!!2270433976.jpg_600x600.jpg_.webp\", \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/2270433976\\/O1CN01g1GgbE1fF3FafsbAm_!!2270433976.jpg_600x600.jpg_.webp\", \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/2270433976\\/O1CN012kVnmu1fF3FajVDUa_!!2270433976.jpg_600x600.jpg_.webp\", \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/2270433976\\/O1CN01jiJ1Wx1fF3FbLtYIh_!!2270433976.jpg_600x600.jpg_.webp\", \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/2270433976\\/O1CN01bIZgsd1fF3FbsFTPp_!!2270433976.jpg_600x600.jpg_.webp\", \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/2270433976\\/O1CN01WL9a9W1fF3FaJfGIP_!!2270433976.jpg_600x600.jpg_.webp\"],\n" +
//                "\t\t\"price\": 9.9,\n" +
//                "\t\t\"original_price\": 19.9,\n" +
//                "\t\t\"is_tmall\": 1,\n" +
//                "\t\t\"quan_price\": 10,\n" +
//                "\t\t\"quan_start_time\": \"2019.01.21\",\n" +
//                "\t\t\"quan_time\": \"2019.01.23\",\n" +
//                "\t\t\"sell_num\": 4857,\n" +
//                "\t\t\"goods_id\": \"544040534929\",\n" +
//                "\t\t\"start_time\": 1548000000,\n" +
//                "\t\t\"goods_end_time\": 1548259199,\n" +
//                "\t\t\"is_video\": 0,\n" +
//                "\t\t\"video_url\": \"\",\n" +
//                "\t\t\"goods_tag\": [\"\\u5305\\u90ae\", \"\\u8fd0\\u8d39\\u9669\"],\n" +
//                "\t\t\"url\": \"https:\\/\\/uland.taobao.com\\/coupon\\/edetail?e=2d8yf%2F5iDQcGQASttHIRqe75dN6jrs2bMNiHGhM4pe2svA1IFolYCa2nrHMOn43PMTlTF37lHedvaJnaN05BB7DgrwtX2alfhLjdYI0pIJBToedDTP1a7xemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwR6Mtjlukw%2BlCcS%2FKmRHpSw&traceId=0b08442915480507641268035e&union_lens=lensId:0b093c83_0b33_1686f04117b_4da3&xId=jwDn44x0FcjvRCK647X2N0xl21dO4gopmi6MgN63rT3gz05Kc6rUwTLweb5aqEydJoPWtmy6MTa3qzmQj87YIp&activityId=cce2b2f6648a4294aefd5e3501c7fe21\",\n" +
//                "\t\t\"goods_price_type\": \"\\u5929\\u732b\\u4ef7\",\n" +
//                "\t\t\"goods_price_text\": \"\\u5238\\u540e\\u4ef7\",\n" +
//                "\t\t\"goods_button_text\": \"\\u9886\\u5238\\u8d2d\\u4e70\",\n" +
//                "\t\t\"goods_quan_text\": \"\\u7acb\\u5373\\u9886\\u5238\",\n" +
//                "\t\t\"label\": {\n" +
//                "\t\t\t\"bg_image\": \"\",\n" +
//                "\t\t\t\"bg_color\": \"\",\n" +
//                "\t\t\t\"image\": \"\",\n" +
//                "\t\t\t\"image_width\": 0,\n" +
//                "\t\t\t\"image_height\": 0,\n" +
//                "\t\t\t\"image_right\": \"\",\n" +
//                "\t\t\t\"image_right_width\": 0,\n" +
//                "\t\t\t\"image_right_height\": 0,\n" +
//                "\t\t\t\"component\": 0,\n" +
//                "\t\t\t\"count_down_end\": 1548230460,\n" +
//                "\t\t\t\"text\": \"\",\n" +
//                "\t\t\t\"text_color\": \"\",\n" +
//                "\t\t\t\"text_font\": 0,\n" +
//                "\t\t\t\"ranking_top_text\": \"\",\n" +
//                "\t\t\t\"ranking_top_font\": 0,\n" +
//                "\t\t\t\"ranking_top_color\": \"\",\n" +
//                "\t\t\t\"ranking_bottom_text\": \"\",\n" +
//                "\t\t\t\"ranking_bottom_font\": 0,\n" +
//                "\t\t\t\"ranking_bottom_color\": \"\",\n" +
//                "\t\t\t\"count_down_text\": \"\",\n" +
//                "\t\t\t\"count_down_font\": 0,\n" +
//                "\t\t\t\"count_down_color\": \"\",\n" +
//                "\t\t\t\"show_label\": 0\n" +
//                "\t\t},\n" +
//                "\t\t\"store\": {\n" +
//                "\t\t\t\"seller_id\": \"2270433976\",\n" +
//                "\t\t\t\"dsr\": 4.8,\n" +
//                "\t\t\t\"dsr_level\": 0,\n" +
//                "\t\t\t\"service\": 4.8,\n" +
//                "\t\t\t\"service_level\": 1,\n" +
//                "\t\t\t\"delivery\": 4.8,\n" +
//                "\t\t\t\"delivery_level\": 1,\n" +
//                "\t\t\t\"shop_name\": \"\\u6c38\\u5065\\u6237\\u5916\\u65d7\\u8230\\u5e97\",\n" +
//                "\t\t\t\"shop_logo\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/\\/fa\\/2e\\/TB1fLetLpXXXXcWaXXXSutbFXXX.jpg\",\n" +
//                "\t\t\t\"show_button\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1\n" +
//                "\t\t},\n" +
//                "\t\t\"recommend\": [{\n" +
//                "\t\t\t\"title\": \"2019\\u513f\\u7ae5\\u6f4d\\u574a\\u8001\\u9e70\\u8774\\u8776\\u5361\\u901a\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 22,\n" +
//                "\t\t\t\"price\": 9.9,\n" +
//                "\t\t\t\"original_price\": 19.9,\n" +
//                "\t\t\t\"coupon_value\": 10,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/1612048455\\/O1CN01Qbs1Pe2CKREOagl2K_!!1612048455.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18205018,\n" +
//                "\t\t\t\"goods_id\": \"585872174424\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"bfe8a82a530e4b6db5ef45297abf0276\",\n" +
//                "\t\t\t\"start_time\": 1548172800,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a\\u65b0\\u6b3e\\u5927\\u578b\\u7acb\\u4f53\\u7ffc\\u9f99\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 398,\n" +
//                "\t\t\t\"price\": 39.99,\n" +
//                "\t\t\t\"original_price\": 44.99,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/772971083\\/O1CN01WqdvvR1Js3QpyUvEZ_!!772971083.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18153547,\n" +
//                "\t\t\t\"goods_id\": \"15998194773\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"b0add419df494f19b081fd0c986b9b4c\",\n" +
//                "\t\t\t\"start_time\": 1547790737,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u5927\\u578b\\u8774\\u8776\\u6c99\\u71d5\\u9e70\\u5fae\\u98ce\\u6613\\u98de\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 890,\n" +
//                "\t\t\t\"price\": 9.9,\n" +
//                "\t\t\t\"original_price\": 19.9,\n" +
//                "\t\t\t\"coupon_value\": 10,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/TB25b0Bl4PI8KJjSspfXXcCFXXa_!!3592695041.png\",\n" +
//                "\t\t\t\"id\": 18195587,\n" +
//                "\t\t\t\"goods_id\": \"563830990489\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"2b59487d73c645c480c81eeeb942c79c\",\n" +
//                "\t\t\t\"start_time\": 1548120788,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u98ce\\u4e50\\u30112019\\u65b0\\u6b3e\\u4e03\\u5f69\\u51e4\\u5c3e\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 416,\n" +
//                "\t\t\t\"price\": 6.9,\n" +
//                "\t\t\t\"original_price\": 11.9,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/279515528\\/O1CN01mcoeSb1qhrwnliGVc_!!279515528.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18213226,\n" +
//                "\t\t\t\"goods_id\": \"580302999011\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"fbab122ba28047feb878c4b420c21a2f\",\n" +
//                "\t\t\t\"start_time\": 1548227348,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u65b0\\u6b3e\\u5361\\u901a\\u98ce\\u7b5d\\u8001\\u9e70\\u8774\\u8776\\u6210\\u4eba\\u513f\\u7ae5\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 153,\n" +
//                "\t\t\t\"price\": 9.9,\n" +
//                "\t\t\t\"original_price\": 14.9,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i3\\/1844654127\\/O1CN01GKBEgo1gMD3dre3c3_!!1844654127.png\",\n" +
//                "\t\t\t\"id\": 18178833,\n" +
//                "\t\t\t\"goods_id\": \"585449036234\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 0,\n" +
//                "\t\t\t\"coupon_id\": \"35a9b9572da24feba74bc42f9b2a247e\",\n" +
//                "\t\t\t\"start_time\": 1548034337,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a\\u65b0\\u6b3e\\u98ce\\u7b5d\\u513f\\u7ae5\\u6613\\u98de\\u9001\\u8f6c\\u8f6e\\u624b\\u63e1\\u8f6e\",\n" +
//                "\t\t\t\"sell_num\": 2341,\n" +
//                "\t\t\t\"price\": 9.97,\n" +
//                "\t\t\t\"original_price\": 14.97,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i3\\/3030065956\\/O1CN01tnqUPY1trtSIyLAph_!!3030065956.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18178853,\n" +
//                "\t\t\t\"goods_id\": \"571538491018\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"46aa26dbfd27494d800f3e8e8f0c03e7\",\n" +
//                "\t\t\t\"start_time\": 1548034389,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a\\u9177\\u7fd4\\u65b0\\u6b3e\\u8f6f\\u4f53\\u7ae0\\u9c7c\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 2136,\n" +
//                "\t\t\t\"price\": 13.96,\n" +
//                "\t\t\t\"original_price\": 18.96,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/3030065956\\/O1CN01EU3onI1trtSN0hFkt_!!3030065956.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18178700,\n" +
//                "\t\t\t\"goods_id\": \"561579872807\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"46aa26dbfd27494d800f3e8e8f0c03e7\",\n" +
//                "\t\t\t\"start_time\": 1548033970,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a\\u9177\\u7fd4\\u98de\\u5929\\u5f69\\u8679\\u98ce\\u7b5d\\u957f\\u5c3e\\u591a\\u5f69\\u513f\\u7ae5\",\n" +
//                "\t\t\t\"sell_num\": 124,\n" +
//                "\t\t\t\"price\": 12.99,\n" +
//                "\t\t\t\"original_price\": 17.99,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/3417140508\\/O1CN01qafKbI1FchgBDg2DC_!!3417140508.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18172677,\n" +
//                "\t\t\t\"goods_id\": \"560496887993\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"5fbfe108b2994a14b614899271f08d1c\",\n" +
//                "\t\t\t\"start_time\": 1547949783,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a2019\\u65b0\\u6b3e\\u84dd\\u8774\\u8776\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 161,\n" +
//                "\t\t\t\"price\": 29.99,\n" +
//                "\t\t\t\"original_price\": 34.99,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/772971083\\/O1CN01tqHJYm1Js3QxyVPtz_!!772971083.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18152768,\n" +
//                "\t\t\t\"goods_id\": \"43831474188\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"728108daa2214901bdc5b218cbb9152f\",\n" +
//                "\t\t\t\"start_time\": 1547787544,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u4e70\\u4e8c\\u9001\\u4e00\\u3011diy\\u5361\\u901a\\u5fae\\u98ce\\u6613\\u98de\\u521d\\u5b66\\u8005\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 314,\n" +
//                "\t\t\t\"price\": 7.9,\n" +
//                "\t\t\t\"original_price\": 9.9,\n" +
//                "\t\t\t\"coupon_value\": 2,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i3\\/2097692230\\/O1CN01B18W1I1SLNimho1L6_!!2097692230.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18133332,\n" +
//                "\t\t\t\"goods_id\": \"585269130032\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"b417a435e32c4cb5a8d506276c105923\",\n" +
//                "\t\t\t\"start_time\": 1547740800,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u4e70\\u4e8c\\u9001\\u4e00\\u3011diy\\u5361\\u901a\\u5fae\\u98ce\\u6613\\u98de\\u521d\\u5b66\\u8005\\u98ce\\u7b5d\",\n" +
//                "\t\t\t\"sell_num\": 345,\n" +
//                "\t\t\t\"price\": 7.9,\n" +
//                "\t\t\t\"original_price\": 9.9,\n" +
//                "\t\t\t\"coupon_value\": 2,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/2261089795\\/O1CN01MJEk6Q2ME9xCgPw1o_!!2261089795.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18164855,\n" +
//                "\t\t\t\"goods_id\": \"585410479765\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"e4c83aacf68c4cd7834ada9ecb68440c\",\n" +
//                "\t\t\t\"start_time\": 1547867325,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a\\u8001\\u9e70\\u98ce\\u7b5d\\u6210\\u4eba\\u5927\\u578b\\u5fae\\u98ce\\u6613\\u98de\\u5361\\u901a\",\n" +
//                "\t\t\t\"sell_num\": 1935,\n" +
//                "\t\t\t\"price\": 11.8,\n" +
//                "\t\t\t\"original_price\": 16.8,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/2355529640\\/O1CN01Filo6w2L5AYoocE7A_!!2355529640.jpg_310x310.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18210610,\n" +
//                "\t\t\t\"goods_id\": \"44083704175\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"b8e860c110f446fca101f8ae91c573d1\",\n" +
//                "\t\t\t\"start_time\": 1548259200,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6f4d\\u574a\\u9ad8\\u6863\\u513f\\u7ae5\\u6210\\u4eba\\u521d\\u5b66\\u8005\\u98ce\\u7b5d\\u5fae\\u98ce\\u6613\\u98de\",\n" +
//                "\t\t\t\"sell_num\": 3622,\n" +
//                "\t\t\t\"price\": 21.8,\n" +
//                "\t\t\t\"original_price\": 26.8,\n" +
//                "\t\t\t\"coupon_value\": 5,\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/709718915\\/O1CN010QJxC62Fj7QAYOngV_!!709718915.png\",\n" +
//                "\t\t\t\"id\": 18213571,\n" +
//                "\t\t\t\"goods_id\": \"573271709896\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"d762d3002af245f6b64413a81689d5c9\",\n" +
//                "\t\t\t\"start_time\": 1548228545,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}],\n" +
//                "\t\t\"sales\": [{\n" +
//                "\t\t\t\"title\": \"\\u5c48\\u81e3\\u6c0f\\u70ed\\u5356\\uff01\\u7ef4\\u751f\\u7d20\\u7709\\u7b145\\u652f\\u88c5\",\n" +
//                "\t\t\t\"sell_num\": \"1390\",\n" +
//                "\t\t\t\"price\": 19.9,\n" +
//                "\t\t\t\"original_price\": \"59.90\",\n" +
//                "\t\t\t\"coupon_value\": \"40.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/2457980752\\/O1CN01ZYGjzy1HQSKeMlTLW_!!0-item_pic.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": \"18195757\",\n" +
//                "\t\t\t\"goods_id\": \"585493495176\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"9833f4db0b11480c93e572c6a072ae92\",\n" +
//                "\t\t\t\"start_time\": 1548121205,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": \"1\",\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u30102\\u74f6\\u3011\\u7f57\\u838e\\u5e84\\u56ed\\u00b7\\u897f\\u73ed\\u7259\\u5e72\\u7ea2\\u8461\\u8404\\u9152\",\n" +
//                "\t\t\t\"sell_num\": \"2020\",\n" +
//                "\t\t\t\"price\": 29.9,\n" +
//                "\t\t\t\"original_price\": \"49.90\",\n" +
//                "\t\t\t\"coupon_value\": \"20.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/1832800315\\/O1CN01U7bPZF1ECJFl4TeDi_!!1832800315.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": \"18203359\",\n" +
//                "\t\t\t\"goods_id\": \"550391243679\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"93986d64155344d583daf3b66c9bb08e\",\n" +
//                "\t\t\t\"start_time\": 1548154800,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": \"1\",\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6d77\\u5e95\\u635e\\u9187\\u9999\\u9ebb\\u8fa3\\u725b\\u6cb9\\u706b\\u9505\\u5e95\\u6599150g*3\\u888b\",\n" +
//                "\t\t\t\"sell_num\": \"12941\",\n" +
//                "\t\t\t\"price\": 22.5,\n" +
//                "\t\t\t\"original_price\": \"27.50\",\n" +
//                "\t\t\t\"coupon_value\": \"5.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/3405505114\\/O1CN011neFxKSVICAgKTY_!!3405505114.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": \"18189644\",\n" +
//                "\t\t\t\"goods_id\": \"574886935644\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"350e346628b84cada467809e20ae73e8\",\n" +
//                "\t\t\t\"start_time\": 1548086400,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": \"1\",\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u56db\\u5ddd\\u7279\\u4ea7\\u706b\\u9505\\u5e95\\u6599200g+\\u8fa3\\u6912\\u97622\\u5305\",\n" +
//                "\t\t\t\"sell_num\": \"2555\",\n" +
//                "\t\t\t\"price\": 13.8,\n" +
//                "\t\t\t\"original_price\": \"16.80\",\n" +
//                "\t\t\t\"coupon_value\": \"3.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/3969124332\\/TB2xxwTE.R1BeNjy0FmXXb0wVXa_!!3969124332.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": \"18187154\",\n" +
//                "\t\t\t\"goods_id\": \"573324217772\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"9abb6d1b0a04423fabf8bab48775778c\",\n" +
//                "\t\t\t\"start_time\": 1548055512,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": \"1\",\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u6bdb\\u5462\\u6175\\u61d2\\u54c8\\u4f26\\u88e4\\u79cb\\u51ac\\u97e9\\u7248\\u767e\\u642d\\u5bbd\\u677e\\u9ad8\\u8170\\u4e5d\\u5206\",\n" +
//                "\t\t\t\"sell_num\": 3179,\n" +
//                "\t\t\t\"price\": 43,\n" +
//                "\t\t\t\"original_price\": \"58.00\",\n" +
//                "\t\t\t\"coupon_value\": \"15.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/2855496824\\/O1CN0120HRDlxXuXiEN5R_!!2855496824.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18122358,\n" +
//                "\t\t\t\"goods_id\": \"580168614534\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"efed33da5de549208cd95b8853ba9e1e\",\n" +
//                "\t\t\t\"start_time\": 1547654400,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u4e2d\\u5e74\\u7fbd\\u7ed2\\u670d\\u51ac\\u88c5\\u4e2d\\u957f\\u6b3e\\u5916\\u5957\",\n" +
//                "\t\t\t\"sell_num\": 682,\n" +
//                "\t\t\t\"price\": 178,\n" +
//                "\t\t\t\"original_price\": \"248.00\",\n" +
//                "\t\t\t\"coupon_value\": \"70.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/3391730483\\/O1CN01fD946u1FRFmkue8Yz_!!0-item_pic.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18205201,\n" +
//                "\t\t\t\"goods_id\": \"580858009336\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"47f7cf3af1e844e79f3fa215f80b4da9\",\n" +
//                "\t\t\t\"start_time\": 1548162544,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u5929\\u732b\\u3011\\u4e00\\u5b57\\u9886\\u6bdb\\u8863\\u6253\\u5e95\\u886b\\u957f\\u8896\\u9488\\u7ec7\\u886b\\u4e0a\\u8863\\u5973\",\n" +
//                "\t\t\t\"sell_num\": 1015,\n" +
//                "\t\t\t\"price\": 19.9,\n" +
//                "\t\t\t\"original_price\": \"59.90\",\n" +
//                "\t\t\t\"coupon_value\": \"40.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/O1CN01oWEWzg1ghjf2ZsRWi_!!3383474174.png\",\n" +
//                "\t\t\t\"id\": 18211893,\n" +
//                "\t\t\t\"goods_id\": \"583404922119\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"93d2913b1dac4907b1ab40da8b4b3073\",\n" +
//                "\t\t\t\"start_time\": 1548222967,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u79cb\\u51ac\\u65b0\\u6b3e\\u52a0\\u7ed2\\u52a0\\u539a\\u725b\\u4ed4\\u88e4\\u5973\",\n" +
//                "\t\t\t\"sell_num\": 187,\n" +
//                "\t\t\t\"price\": 54,\n" +
//                "\t\t\t\"original_price\": \"89.00\",\n" +
//                "\t\t\t\"coupon_value\": \"35.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i3\\/4001339029\\/O1CN01GVtXv42GZKYdlBXuf_!!4001339029.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18202435,\n" +
//                "\t\t\t\"goods_id\": \"582946134013\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"b5083334f219413cb0c3c56b97db1950\",\n" +
//                "\t\t\t\"start_time\": 1548145869,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u52a0\\u7ed2\\u52a0\\u539a\\u91d1\\u4e1d\\u7ed2\\u5bbd\\u677e\\u536b\\u8863\\u5973\",\n" +
//                "\t\t\t\"sell_num\": 2320,\n" +
//                "\t\t\t\"price\": 49,\n" +
//                "\t\t\t\"original_price\": \"59.00\",\n" +
//                "\t\t\t\"coupon_value\": \"10.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/3676583213\\/O1CN01cSfcll1Zbb2VSZjxz_!!0-item_pic.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18160959,\n" +
//                "\t\t\t\"goods_id\": \"582713172266\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"a830209ac739467ca4bd94cb8e15ce5a\",\n" +
//                "\t\t\t\"start_time\": 1547827200,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u5988\\u5988\\u88c5\\u51ac\\u88c5\\u52a0\\u539a\\u5927\\u6bdb\\u9886\\u68c9\\u670d\",\n" +
//                "\t\t\t\"sell_num\": 390,\n" +
//                "\t\t\t\"price\": 138,\n" +
//                "\t\t\t\"original_price\": \"178.00\",\n" +
//                "\t\t\t\"coupon_value\": \"40.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/3382146323\\/O1CN01B0FThQ1wZynkHiLo3_!!0-item_pic.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18213879,\n" +
//                "\t\t\t\"goods_id\": \"582412578414\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"2a4a13e86b3f4219a677bf3f234d3a7b\",\n" +
//                "\t\t\t\"start_time\": 1548229810,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u5c0f\\u811a\\u5916\\u7a7f\\u79cb\\u51ac\\u5b63\\u9ed1\\u8272\\u52a0\\u539a\\u88e4\\u5b50\",\n" +
//                "\t\t\t\"sell_num\": 115,\n" +
//                "\t\t\t\"price\": 98,\n" +
//                "\t\t\t\"original_price\": \"198.00\",\n" +
//                "\t\t\t\"coupon_value\": \"100.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/1823989314\\/O1CN01bEoBnE2IfrRgrh34F_!!1823989314.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18213939,\n" +
//                "\t\t\t\"goods_id\": \"581202635126\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 0,\n" +
//                "\t\t\t\"coupon_id\": \"ce754ee220a748a7b5ebb4fe1e361209\",\n" +
//                "\t\t\t\"start_time\": 1548230041,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u52a0\\u7ed2\\u725b\\u4ed4\\u88e4\\u5973\\u51ac\\u5b63\\u52a0\\u539a\\u4fdd\\u6696\\u751f\\u5f39\\u529b\\u5c0f\\u811a\",\n" +
//                "\t\t\t\"sell_num\": 29,\n" +
//                "\t\t\t\"price\": 59,\n" +
//                "\t\t\t\"original_price\": \"89.00\",\n" +
//                "\t\t\t\"coupon_value\": \"30.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/646360154\\/O1CN01uIOG4b1D0ZToB2g4M_!!646360154.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18213726,\n" +
//                "\t\t\t\"goods_id\": \"583110779892\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"13c5d23775f74840a9cfe3a5793f6f0c\",\n" +
//                "\t\t\t\"start_time\": 1548229158,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"acwell\\u827e\\u73c2\\u8587\\u8212\\u7f13\\u4fee\\u62a4\\u9762\\u971c50ml\",\n" +
//                "\t\t\t\"sell_num\": 16060,\n" +
//                "\t\t\t\"price\": 108,\n" +
//                "\t\t\t\"original_price\": \"128.00\",\n" +
//                "\t\t\t\"coupon_value\": \"20.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/2660562403\\/O1CN01SNWqbJ1TccDzZmFFb_!!2660562403.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18187240,\n" +
//                "\t\t\t\"goods_id\": \"568163328573\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"31cb97730b5f4da5bb3e8f420101c56d\",\n" +
//                "\t\t\t\"start_time\": 1548122400,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 3,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u62cd3\\u4ef6\\u3011\\u97e9\\u745f24k\\u9ec4\\u91d1\\u7cbe\\u534e\\u6db23\\u74f6\",\n" +
//                "\t\t\t\"sell_num\": 18228,\n" +
//                "\t\t\t\"price\": 38,\n" +
//                "\t\t\t\"original_price\": \"238.00\",\n" +
//                "\t\t\t\"coupon_value\": \"200.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/1637899820\\/O1CN015Le1HX2MPbr5GCQsa_!!1637899820.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18191611,\n" +
//                "\t\t\t\"goods_id\": \"584530966009\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"e8f9de3a16fe4ef082a75f8ec1082341\",\n" +
//                "\t\t\t\"start_time\": 1548086400,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u79d2\\u6740\\uff01\\u3010\\u6574\\u59574\\u5927\\u672c\\u3011\\u513f\\u7ae5\\u76ca\\u667a\\u8d34\\u7eb8\\u4e66\",\n" +
//                "\t\t\t\"sell_num\": 18547,\n" +
//                "\t\t\t\"price\": 8.8,\n" +
//                "\t\t\t\"original_price\": \"11.80\",\n" +
//                "\t\t\t\"coupon_value\": \"3.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i2\\/1012969403\\/O1CN012JKcgKnqdBE1y0S_!!1012969403.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18184735,\n" +
//                "\t\t\t\"goods_id\": \"521919118076\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"a97b71e767b24a8daa0b9d6f704715f8\",\n" +
//                "\t\t\t\"start_time\": 1548122400,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 3,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u6668\\u5149\\u3011HB\\u4e09\\u89d2\\u6b63\\u59ff\\u94c5\\u7b1450\\u652f\",\n" +
//                "\t\t\t\"sell_num\": 3442,\n" +
//                "\t\t\t\"price\": 12.9,\n" +
//                "\t\t\t\"original_price\": \"15.90\",\n" +
//                "\t\t\t\"coupon_value\": \"3.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/3012913363\\/O1CN01Y6DaHs1aiISrg0zbN_!!3012913363.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18201301,\n" +
//                "\t\t\t\"goods_id\": \"539693751081\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"c2cd0b5653c4494b805247db739e6b3b\",\n" +
//                "\t\t\t\"start_time\": 1548172800,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u7f51\\u7ea2\\u7eaf\\u624b\\u5de5\\u679c\\u5e72\\u7247\\u6c34\\u679c\\u83361\\u76d27\\u5305\",\n" +
//                "\t\t\t\"sell_num\": 4199,\n" +
//                "\t\t\t\"price\": 9.9,\n" +
//                "\t\t\t\"original_price\": \"39.90\",\n" +
//                "\t\t\t\"coupon_value\": \"30.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i3\\/2299663760\\/O1CN01418HAV1de7hZbLgRc_!!2299663760.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18198564,\n" +
//                "\t\t\t\"goods_id\": \"585952112451\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"294f634b3a0f48d0af657d91bed0a8d0\",\n" +
//                "\t\t\t\"start_time\": 1548129188,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u30106.9\\u4e24\\u6761\\u3011iPhone\\u82f9\\u679c\\u624b\\u673a\\u5145\\u7535\\u7ebf\",\n" +
//                "\t\t\t\"sell_num\": 28464,\n" +
//                "\t\t\t\"price\": 5.9,\n" +
//                "\t\t\t\"original_price\": \"15.90\",\n" +
//                "\t\t\t\"coupon_value\": \"10.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/2263324885\\/TB2IsSRbvjM8KJjSZFNXXbQjFXa_!!2263324885.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18209511,\n" +
//                "\t\t\t\"goods_id\": \"538506350538\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"55e8a9985aa941d0bd7ea08fdc231d58\",\n" +
//                "\t\t\t\"start_time\": 1548210430,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u3010\\u8499\\u4e3d\\u4e1d\\u30113\\u5377\\u88c5\\u4e00\\u6b21\\u6027\\u6d17\\u8138\\u5dfe\\u6d01\\u9762\\u5dfe\",\n" +
//                "\t\t\t\"sell_num\": 91571,\n" +
//                "\t\t\t\"price\": 18.7,\n" +
//                "\t\t\t\"original_price\": \"23.70\",\n" +
//                "\t\t\t\"coupon_value\": \"5.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i4\\/3074628709\\/TB2wtX6XzvlJKJjSspnXXblTVXa_!!3074628709.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18210299,\n" +
//                "\t\t\t\"goods_id\": \"577809969991\",\n" +
//                "\t\t\t\"is_new\": 0,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"1cd4dc329dfe4c229ea0ec3809e0dcb8\",\n" +
//                "\t\t\t\"start_time\": 1548213253,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"title\": \"\\u5e7f\\u836f\\u767d\\u4e91\\u5c71\\u8349\\u672c\\u6291\\u83cc\\u6b62\\u75d2\\u53bb\\u811a\\u6c14\\u5957\\u88c5\",\n" +
//                "\t\t\t\"sell_num\": 41942,\n" +
//                "\t\t\t\"price\": 6.9,\n" +
//                "\t\t\t\"original_price\": \"26.90\",\n" +
//                "\t\t\t\"coupon_value\": \"20.00\",\n" +
//                "\t\t\t\"image\": \"https:\\/\\/img.alicdn.com\\/imgextra\\/i1\\/3683157363\\/TB2Wt8UuQ7mBKNjSZFyXXbydFXa_!!3683157363.jpg_480x480.jpg_.webp\",\n" +
//                "\t\t\t\"id\": 18186690,\n" +
//                "\t\t\t\"goods_id\": \"576319912795\",\n" +
//                "\t\t\t\"is_new\": 1,\n" +
//                "\t\t\t\"is_tmall\": 1,\n" +
//                "\t\t\t\"coupon_id\": \"3dc10a524bb44033a9ee397c528b97f5\",\n" +
//                "\t\t\t\"start_time\": 1548086400,\n" +
//                "\t\t\t\"is_video\": 0,\n" +
//                "\t\t\t\"ci\": 0,\n" +
//                "\t\t\t\"cn\": 0,\n" +
//                "\t\t\t\"huodong_type\": 1,\n" +
//                "\t\t\t\"goods_tag_icon\": \"\",\n" +
//                "\t\t\t\"price_tag\": \"\\u5238\\u540e\"\n" +
//                "\t\t}],\n" +
//                "\t\t\"collected\": 0\n" +
//                "\t},\n" +
//                "\t\"total\": 0,\n" +
//                "\t\"page\": 1,\n" +
//                "\t\"size\": 20,\n" +
//                "\t\"cac_id\": \"\",\n" +
//                "\t\"server_time\": 1548230460\n" +
//                "}");
    }


}
