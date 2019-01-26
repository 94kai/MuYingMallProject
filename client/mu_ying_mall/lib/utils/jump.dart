import 'package:flutter/material.dart';
import '../page/product_detail_page.dart';

///跳商详
jumpToProductDetail(context, productId) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => ProductDetailPage(productId)));
}


///跳购物车
jumpToShoppingCart(context) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => ProductDetailPage("data")));
}
