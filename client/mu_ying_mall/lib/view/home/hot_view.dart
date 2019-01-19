import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'package:flutter_page_indicator/flutter_page_indicator.dart';

class HotView extends StatefulWidget {
  @override
  HotViewState createState() {
    return new HotViewState();
  }
}

class HotViewState extends State<HotView> {
  ///热门banner的图片集合
  List<dynamic> imageUrls = [];

  @override
  void initState() {
    super.initState();
    get("queryHotBanner", (data) {
      setState(() {
        imageUrls = data;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          _renderHotBanner(),
        ],
      ),
    );
  }

  _renderHotBanner() {
    return Container(
      width: MediaQuery.of(context).size.width,
      height: MediaQuery.of(context).size.width / 2.5,
      child: Swiper(
          itemBuilder: (BuildContext context, int index) {
            return new Image.network(
              imageUrls[index]['bannerImg'],
              fit: BoxFit.fill,
            );
          },
          itemCount: imageUrls.length,
          autoplay: true,
          pagination: SwiperPagination(
              builder: DotSwiperPaginationBuilder(
                  color: Colors.black54,
                  activeColor: Colors.pink,
                  size: 7,
                  activeSize: 7))),
    );
  }
}
