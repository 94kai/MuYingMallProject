import 'package:flutter/material.dart';
import 'top_view.dart';
import 'detail_list_view.dart';
import '../../utils/network.dart';

class DetailRootView extends StatefulWidget {
  final productId;

  DetailRootView(this.productId);

  @override
  DetailRootViewState createState() {
    return new DetailRootViewState();
  }
}

class DetailRootViewState extends State<DetailRootView> {
  var _opacity = 0.0;

  var data;

  @override
  void initState() {
    super.initState();

    get(
        "queryProductDetailById",
        (data) => setState(() {
              this.data = data;
//              for (var value in data.keys) {
//                print("$value : ${data[value]}");
//              }
            }),
        params: {'productId': "${widget.productId}"});
  }

  @override
  Widget build(BuildContext context) {
    if(data == null){
      return Container();
    }
    return Stack(
      children: <Widget>[
        DetailListView((opacity) => changeTitleOpacity(opacity), data),
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
