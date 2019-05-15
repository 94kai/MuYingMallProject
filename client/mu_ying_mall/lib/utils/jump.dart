import 'package:flutter/material.dart';
import 'package:mu_ying_mall/page/product_detail_page.dart';
import 'package:mu_ying_mall/page/shopping_car_page.dart';
import 'package:mu_ying_mall/page/login_page.dart';
import 'package:mu_ying_mall/page/settle_account_page.dart';
import 'package:mu_ying_mall/page/address_page.dart';
import 'package:mu_ying_mall/page/edit_address_page.dart';

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

///跳收货地址页
jumpToAddress(context, totalMoney) {
  Navigator.of(context)
      .push(MaterialPageRoute(builder: (context) => AddressPage(totalMoney)));
}

///跳收货地址编辑页
jumpToEditAddress(context, address, userName,onResult) {
  Navigator.of(context).push(MaterialPageRoute(
      builder: (context) => EditAddressPage(address, userName)))
      .then((result) => onResult(result));
}

///跳结算页
jumpToSettleAccount(context, totalMoney) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => SettleAccountPage(totalMoney)));
}
