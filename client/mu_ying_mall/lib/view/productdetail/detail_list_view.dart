import 'package:flutter/material.dart';

import '../../utils/network.dart';
import 'package:flutter_swiper/flutter_swiper.dart';

class DetailListView extends StatefulWidget {
  final headImageAspectRatio = 1.36;
  var headImageHeight;
  Function changeTitleopacity;

  DetailListView(this.changeTitleopacity);

  @override
  DetailListViewState createState() {
    return new DetailListViewState();
  }
}

class DetailListViewState extends State<DetailListView> {
  var data;

  @override
  void initState() {
    super.initState();

    setState(() {
      data = getDetail()['data'];
      for (var value in data.keys) {
        print("$value : ${data[value]}");
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    widget.headImageHeight =
        MediaQuery.of(context).size.width / widget.headImageAspectRatio;

    return Container(
        color: Colors.white,
        child: NotificationListener(
          onNotification: dataNofify,
          child: ListView.builder(
              itemBuilder: (BuildContext context, int index) =>
                  _buildItem(context, index)),
        ));
  }

  _buildItem(BuildContext context, int index) {
    if (index == 0) {
      return AspectRatio(
        aspectRatio: widget.headImageAspectRatio,
        child: Container(
          child: Swiper(
              itemBuilder: (BuildContext context, int index) {
                return Image.network(
                  data['pic_list'][index],
                  fit: BoxFit.fitWidth,
                );
              },
              itemCount: data['pic_list'].length,
              autoplay: false,
              pagination: SwiperPagination(
                  builder: DotSwiperPaginationBuilder(
                      color: Colors.black54,
                      activeColor: Colors.pink,
                      size: 7,
                      activeSize: 7))),
          color: Colors.pink,
        ),
      );
    } else if (index == 1) {
      return Container(
        padding: EdgeInsets.fromLTRB(10, 5, 10, 5),
        color: Colors.white,
        height: 100,
        child: Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.only(bottom: 10),
              alignment: AlignmentDirectional.centerStart,
              child: Text(
                data['title'],
                maxLines: 1,
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                textAlign: TextAlign.start,
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Row(
                  children: <Widget>[
                    Text(
                      "￥",
                      style: TextStyle(
                          color: Colors.red,
                          fontSize: 14,
                          fontWeight: FontWeight.bold),
                    ),
                    Text(
                      "${data['original_price']}",
                      style: TextStyle(
                          color: Colors.red,
                          fontSize: 18,
                          fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                Text(
                  "已售${data['sell_num']}件  ",
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 14,
                  ),
                )
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                Row(children: _buildGoodsTag(data['goods_tag'])),
              ],
            ),
          ],
        ),
      );
    } else if (index == 2) {
      return Container(
        color: Colors.black12,
        height: 8,
      );
    }
  }

  bool dataNofify(Notification notification) {
    if (notification is ScrollUpdateNotification) {
      if (notification.metrics.axisDirection == AxisDirection.down ||
          notification.metrics.axisDirection == AxisDirection.up) {
        if (notification.metrics.extentBefore <
            (widget.headImageHeight -
                kTextTabBarHeight -
                MediaQuery.of(context).padding.top)) {
          widget.changeTitleopacity(notification.metrics.extentBefore /
              (widget.headImageHeight -
                  kTextTabBarHeight -
                  MediaQuery.of(context).padding.top));
        } else {
          widget.changeTitleopacity(1.0);
        }
      }
    }
    return true;
  }

  ///构建goodstag（包邮、运费险等）
  _buildGoodsTag(goodsTag) {
    return goodsTag
        .map<Widget>((tag) => Row(
              children: <Widget>[
                Icon(Icons.bookmark_border,color: Color(0xFFFF8A80),)
                ,
                Text(
                  tag,
                  style: TextStyle(
                    color: Colors.grey,
                  ),
                )
              ],
            ))
        .toList();
  }
}
