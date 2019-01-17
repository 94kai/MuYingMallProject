import 'package:flutter/material.dart';
import '../page/home_page.dart';
import '../page/community_page.dart';
import '../page/shopping_car_page.dart';
import '../page/my_page.dart';

PageView renderMainPageView(controller, onPageChange) {
  return PageView(
    controller: controller,
    onPageChanged: (index) => onPageChange(index),
    children: <Widget>[
      HomePage(),
      CommunityPage(),
      ShoppingCarPage(),
      MyPage(),
    ],
  );
}

//首页底部bar
renderBottomTabBar(tabController, bottomTabBarData, onBottomTabClick) {
  return Container(
    color: Colors.pink,
    child: TabBar(
      onTap: (index) => onBottomTabClick(index),
      labelColor: Colors.white,
      unselectedLabelColor: Colors.white70,
      labelStyle: TextStyle(fontWeight: FontWeight.bold),
      controller: tabController,
      unselectedLabelStyle: TextStyle(fontWeight: FontWeight.bold),
      tabs: _renderBottomTab(bottomTabBarData),
    ),
  );
}

//渲染底部tab
List<Widget> _renderBottomTab(List<dynamic> bottomTabBarData) {
  return bottomTabBarData.map<Widget>((data) {
    return Tab(
        child: Padding(
            padding: const EdgeInsets.fromLTRB(0, 5, 0, 0),
            child: Column(
              children: <Widget>[
                Icon(data['icon']),
                Text(
                  data['title'],
                  style: TextStyle(fontSize: 10),
                ),
              ],
            )));
  }).toList();
}
