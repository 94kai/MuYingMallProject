import 'package:flutter/material.dart';
import '../view/productdetail/detail_root_view.dart';
import '../view/productdetail/bottom_view.dart';

class ProductDetailPage extends StatefulWidget {
  //商品列表传来的商品id
  final productId;

  ProductDetailPage(this.productId);

  @override
  ProductDetailPageState createState() {
    return new ProductDetailPageState();
  }
}

class ProductDetailPageState extends State<ProductDetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: DetailRootView(widget.productId),
      bottomNavigationBar: BottomView(widget.productId),
    );
  }
}
