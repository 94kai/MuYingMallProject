import 'package:flutter/material.dart';
import '../../utils/network.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'detail_base_info_view.dart';
import 'detail_store_view.dart';
import 'detail_recommend_view.dart';

class DetailListView extends StatefulWidget {
  final headImageAspectRatio = 1.36;
  var headImageHeight;
  Function changeTitleopacity;
  var data;

  DetailListView(this.changeTitleopacity, this.data);

  @override
  DetailListViewState createState() {
    return new DetailListViewState();
  }
}

class DetailListViewState extends State<DetailListView> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    widget.headImageHeight =
        MediaQuery.of(context).size.width / widget.headImageAspectRatio;

    return Container(
        child: NotificationListener(
      onNotification: dataNofify,
      child: ListView.builder(
          itemBuilder: (BuildContext context, int index) => /**/
              _buildItem(context, index)),
    ));
  }

  _buildItem(BuildContext context, int index) {
    if (index == 0) {
      return _buildImageView();
    } else if (index == 1) {
      return DetailBaseInfoView(widget.data);
    } else if (index == 2) {
      return Container(
        color: Colors.black12,
        height: 8,
      );
    } else if (index == 3) {
      return DetailStoreView(widget.data);
    } else if (index == 4) {
      return Container(
        color: Colors.black12,
        height: 8,
      );
    } else if (index == 5) {
      if (widget.data['recommend'] == null) {
        return Container();
      }
      return DetailRecomendView(widget.data['recommend']);
    } else if (index == 6) {
      return Container(
        color: Colors.black12,
        height: 8,
      );
    }
  }

  ///构建头部轮播图view
  _buildImageView() {
    var bannerImages;
    if (widget.data == null ||
        widget.data['pic_list'] == null ||
        widget.data['pic_list'].length == 0) {
      bannerImages = [""];
    } else {
      bannerImages = widget.data['pic_list'];
    }
    return AspectRatio(
      aspectRatio: widget.headImageAspectRatio,
      child: Container(
        child: Swiper(
            itemBuilder: (BuildContext context, int index) {
              return Image.network(
                bannerImages[index],
                fit: BoxFit.fitWidth,
              );
            },
            itemCount: bannerImages.length,
            autoplay: false,
            pagination: SwiperPagination(
                builder: DotSwiperPaginationBuilder(
                    color: Colors.black54,
                    activeColor: Colors.pink,
                    size: 7,
                    activeSize: 7))),
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
