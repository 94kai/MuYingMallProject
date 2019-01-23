import 'package:flutter/material.dart';
import 'top_view.dart';
import 'detail_list_view.dart';

class DetailRootView extends StatefulWidget {
  @override
  DetailRootViewState createState() {
    return new DetailRootViewState();
  }
}

class DetailRootViewState extends State<DetailRootView> {
  var _opacity = 0.0;

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        DetailListView((opacity) => changeTitleOpacity(opacity)),
        TopView(_opacity),
      ],
    );
  }

  changeTitleOpacity(opacity) {
    setState(() {
      _opacity = opacity;
    });
  }
}
