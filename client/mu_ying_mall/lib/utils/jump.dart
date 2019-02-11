import 'package:flutter/material.dart';
import '../page/product_detail_page.dart';
import '../page/shopping_car_page.dart';
import '../page/login_page.dart';
import '../page/settle_account_page.dart';

///跳商详
jumpToProductDetail(context, productId) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => ProductDetailPage(productId)));
}

///跳购物车
jumpToShoppingCart(context) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => ShoppingCartPage()));
}

///跳登录页
jumpToLogin(context, onResult) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => LoginPage()))
      .then((result) => onResult(result));
}

///跳结算页
jumpToSettleAccount(context, totalMoney) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => SettleAccountPage(totalMoney)));
}
