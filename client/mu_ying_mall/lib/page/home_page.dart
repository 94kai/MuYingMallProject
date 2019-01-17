import 'package:flutter/material.dart';
import 'package:mu_ying_mall/view/home/appbar.dart';
import '../utils/network.dart';

class HomePage extends StatefulWidget {
  @override
  HomePageState createState() {
    return new HomePageState();
  }
}

class HomePageState extends State<HomePage>
    with SingleTickerProviderStateMixin<HomePage> {
  TabController _topTabController;
  PageController _pageController;
  List<dynamic> _categorys = [];

  @override
  void initState() {
    super.initState();
    _topTabController = TabController(length: 50, vsync: this);
    _pageController = PageController();
    get("queryAllCategory", (categorys) {
      setState(() {
        _categorys = categorys;
      });
    });
  }

  @override
  void dispose() {
    super.dispose();
    _topTabController.dispose();
    _pageController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    var topTabBar =
        renderTopTabBar(_topTabController, _categorys, onTopTabBarClick);
    final appBarHeight = Size.fromHeight(kToolbarHeight +
            (topTabBar?.preferredSize?.height ?? 0.0) +
            MediaQuery.of(context).padding.top)
        .height;
    return Container(
      color: Colors.black,
      child: Column(
        children: <Widget>[
          Container(
            height: appBarHeight,
            child: AppBar(
              title: renderTitleView(),
              bottom: topTabBar,
              titleSpacing: 0,
            ),
          ),
          Expanded(
              flex: 1,
              child: PageView(
                onPageChanged: (index) => _topTabController.animateTo(index),
                controller: _pageController,
                children: <Widget>[
                  Container(
                    margin: EdgeInsets.all(5),
                    color: Colors.red,
                  ),
                  Container(
                    color: Colors.orange,
                  ),
                ],
              )),
        ],
      ),
    );
  }

  //home 顶部tab点击
  onTopTabBarClick(index) {
    _pageController.jumpToPage(index);
  }
}
