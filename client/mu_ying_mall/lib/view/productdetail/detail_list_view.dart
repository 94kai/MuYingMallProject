import 'package:flutter/material.dart';

import '../../utils/network.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'detail_base_info_view.dart';
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
      return _buildImageView();
    } else if (index == 1) {
      return DetailBaseInfoView(data);
    } else if (index == 2) {
      return Container(
        color: Colors.black12,
        height: 8,
      );
    }
  }

  ///构建头部轮播图view
  _buildImageView() {
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
}
