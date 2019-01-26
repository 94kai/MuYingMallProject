package com.lq.muyingmall.domain;

import java.util.List;

public class ProductDetail {
    private long id;

    private String title;

    private float original_price;

    private int sell_num;

    private List<String> pic_list;

    private List<String> goods_tag;

    private Store store;

    private List<Product> recommend;

    private String details;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(float original_price) {
        this.original_price = original_price;
    }

    public int getSell_num() {
        return sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public List<String> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<String> pic_list) {
        this.pic_list = pic_list;
    }

    public List<String> getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(List<String> goods_tag) {
        this.goods_tag = goods_tag;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public List<Product> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<Product> recommend) {
        this.recommend = recommend;
    }

    //    private
//    {
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
//    }
    public static class Store {
        private float dsr;
        private float service;
        private float delivery;
        private String shop_name;
        private String shop_logo;

        public float getDsr() {
            return dsr;
        }

        public void setDsr(float dsr) {
            this.dsr = dsr;
        }

        public float getService() {
            return service;
        }

        public void setService(float service) {
            this.service = service;
        }

        public float getDelivery() {
            return delivery;
        }

        public void setDelivery(float delivery) {
            this.delivery = delivery;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }
    }

}
