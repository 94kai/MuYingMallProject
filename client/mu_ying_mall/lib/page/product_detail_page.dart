import 'package:flutter/material.dart';

class ProductDetailPage extends StatefulWidget {
  //商品列表传来的数据源
  final data;

  ProductDetailPage(this.data);

  @override
  ProductDetailPageState createState() {
    return new ProductDetailPageState();
  }
}

class ProductDetailPageState extends State<ProductDetailPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    if (widget.data['type'] == 'banner') {
      return Text('banner');
    }
    return Text(widget.data['title']);
  }
}
