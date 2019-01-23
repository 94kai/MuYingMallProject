import 'package:flutter/material.dart';
import '../page/product_detail_page.dart';

///跳商详
jumpToProductDetail(context, data) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => ProductDetailPage(data)));
}


///跳购物车
jumpToShoppingCart(context) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => ProductDetailPage("data")));
}
