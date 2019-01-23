import 'package:flutter/material.dart';

import '../../utils/network.dart';

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
      print(data);
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
    if (index != 10) {
      return AspectRatio(
        aspectRatio: widget.headImageAspectRatio,
        child: Container(
          child: Image.network(
            data['image'],
            fit: BoxFit.fitWidth,
          ),
          color: Colors.pink,
        ),
      );
    }
  }

  bool dataNofify(Notification notification) {
    if (notification is ScrollUpdateNotification) {
      if (notification.metrics.extentBefore <
          (widget.headImageHeight -
              kTextTabBarHeight -
              MediaQuery.of(context).padding.top)) {
//        print(notification.metrics.extentBefore / 48);
        widget.changeTitleopacity(notification.metrics.extentBefore /
            (widget.headImageHeight -
                kTextTabBarHeight -
                MediaQuery.of(context).padding.top));
      } else {
        widget.changeTitleopacity(1.0);
//        print(1);
      }
    }
    return true;
  }
}
