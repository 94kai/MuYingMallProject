import 'package:flutter/material.dart';
import 'package:mu_ying_mall/page/product_detail_page.dart';
import 'package:mu_ying_mall/page/shopping_car_page.dart';
import 'package:mu_ying_mall/page/login_page.dart';
import 'package:mu_ying_mall/page/settle_account_page.dart';
import 'package:mu_ying_mall/page/address_page.dart';
import 'package:mu_ying_mall/page/edit_address_page.dart';
import 'package:mu_ying_mall/page/main_page.dart';
import 'package:mu_ying_mall/page/server_home_page.dart';
import 'package:mu_ying_mall/page/product_manager_page.dart';
import 'package:mu_ying_mall/page/promotio_manager_page.dart';
import 'package:mu_ying_mall/page/category_manager_page.dart';
import 'package:mu_ying_mall/page/news_manager_page.dart';

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
jumpToEditAddress(context, address, userName, onResult) {
  Navigator.of(context)
      .push(MaterialPageRoute(
          builder: (context) => EditAddressPage(address, userName)))
      .then((result) => onResult(result));
}

///跳分类编辑页
jumpToEditCategory(context, category, onResult) {
//  Navigator.of(context)
//      .push(MaterialPageRoute(
//      builder: (context) => EditAddressPage(category)))
//      .then((result) => onResult(result));
}

///跳结算页
jumpToSettleAccount(context, totalMoney) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => SettleAccountPage(totalMoney)));
}

///跳客户端首页
jumpToClient(context) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => MainPage()));
//  Navigator.pushReplacement(context,
//      MaterialPageRoute(builder: (BuildContext context) => MainPage()));
}

///跳服务端首页
jumpToServer(context) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => ServerHomePage()));
//  Navigator.pushReplacement(context,
//      MaterialPageRoute(builder: (BuildContext context) => ServerHomePage()));
}

///跳商品管理
jumpToProductManager(context) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (BuildContext context) => ProductManagerPage()));
}
///跳分类管理
jumpToCategoryManager(context) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (BuildContext context) => CategoryManagerPage()));
}
///跳资讯管理
jumpToNewsManager(context) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (BuildContext context) => NewsManagerPage()));
}
///跳资讯管理
jumpToPromotionManager(context) {
  Navigator.of(context).push(
      MaterialPageRoute(builder: (BuildContext context) => PromotionManagerPage()));
}
