import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'promotion_view.dart';
import '../common/product_list_view.dart';
import '../../utils/jump.dart';

class HotView extends StatefulWidget {
  @override
  HotViewState createState() {
    return new HotViewState();
  }
}

class HotViewState extends State<HotView>
    with AutomaticKeepAliveClientMixin<HotView> {
  ///热门banner的图片集合
  List<dynamic> banners = [];
  List<dynamic> promotionList = [];
  List<dynamic> newsList = [
    {"news": "暂无资讯"}
  ];
  List<dynamic> productList = [""];

  @override
  initState() {
    super.initState();
    get("queryHotBanner", (data) {
      setState(() {
        banners = data;
      });
    });
    get("queryHotPromotion", (data) {
      setState(() {
        promotionList = data['promotionList'];
        //过滤一下数据，过滤掉商品数小于1的活动
        promotionList = promotionList
            .where((promotion) => promotion["productsList"].length > 1)
            .toList();
        newsList = data['newsList'];
      });
    });
    get("queryProductsByCategory", (data) {
      setState(() {
        productList = data;
      });
    }, params: {"categoryId": "-1"});
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.deepOrange,
      child: ListView.builder(
        padding: EdgeInsets.only(top: 10),
        itemBuilder: (context, index) => _buildItem(context, index),
      ),
    );
  }

  ///渲染轮播图
  _renderHotBanner() {
    return Container(
      width: MediaQuery.of(context).size.width,
      height: MediaQuery.of(context).size.width / 2.5,
      child: Swiper(
          scale: 0.9,
          viewportFraction: 0.8,
          itemBuilder: (BuildContext context, int index) {
            banners[index]['type'] = 'banner';
            return GestureDetector(
              onTap: () => jumpToProductDetail(context, banners[index]),
              child: Image.network(
                banners[index]['url'],
                fit: BoxFit.fill,
              ),
            );
          },
          itemCount: banners.length,
          autoplay: true,
          pagination: SwiperPagination(
              builder: DotSwiperPaginationBuilder(
                  color: Colors.black54,
                  activeColor: Colors.pink,
                  size: 7,
                  activeSize: 7))),
    );
  }

  _buildItem(context, index) {
    if (index == 0) {
      return _renderHotBanner();
    } else if (index == 1) {
      return PromotionView(promotionList, newsList);
    } else {
      return buildProductList(
          productList, index - 2, (data) => jumpToProductDetail(context, data));
    }
  }

  @override
  bool get wantKeepAlive => true;
}
