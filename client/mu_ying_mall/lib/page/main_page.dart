import 'package:flutter/material.dart';
import '../view/main_frame.dart';

final bottomTabBarData = [
  {"title": "首页", "icon": Icons.home},
  {"title": "社区", "icon": Icons.chat_bubble},
  {"title": "购物车", "icon": Icons.shopping_cart},
  {"title": "我的", "icon": Icons.person}
];

//主页面主架构 底部tab
class MainPage extends StatefulWidget {
  @override
  MainPageState createState() {
    return new MainPageState();
  }
}

class MainPageState extends State<MainPage>
    with TickerProviderStateMixin<MainPage> {
  TabController _bottomTabController;
  PageController _pageController;

  @override
  void initState() {
    super.initState();
    _pageController = PageController();
    _bottomTabController =
        TabController(length: bottomTabBarData.length, vsync: this);
  }

  @override
  void dispose() {
    super.dispose();
    _bottomTabController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: renderMainPageView(_pageController, onPageChange),
      bottomNavigationBar: renderBottomTabBar(
          _bottomTabController, bottomTabBarData, onBottomTabClick),
    );
  }

  ///底部tab点击回调
  onBottomTabClick(index) {
    _pageController.jumpToPage(index);
  }
  ///主框架pageView切换的回调
  onPageChange(index){
    _bottomTabController.animateTo(index);
  }
}
