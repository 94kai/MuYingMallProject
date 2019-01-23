import 'package:flutter/material.dart';
import 'package:mu_ying_mall/view/home/appbar.dart';
import '../utils/network.dart';
import '../view/home/hot_view.dart';
import '../view/home/normal_view.dart';

class HomePage extends StatefulWidget {
  @override
  HomePageState createState() {
    return new HomePageState();
  }
}

class HomePageState extends State<HomePage>
    with
        SingleTickerProviderStateMixin<HomePage>,
        AutomaticKeepAliveClientMixin<HomePage> {
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
                children: _renderHomeSubView(_categorys),
              )),
        ],
      ),
    );
  }

  //home 顶部tab点击
  onTopTabBarClick(index) {
    _pageController.jumpToPage(index);
  }

  @override
  bool get wantKeepAlive => true;

  ///渲染首页的界面
  ///目前只有两种类型：1、热门 2、普通。可扩展
  _renderHomeSubView(List categorys) {
    return categorys.map<Widget>((category) {
      if (category['categoryName'].toString() == "热门") {
        return _renderHotView();
      } else {
        return _renderNormalView("${category['categoryId']}");
      }
    }).toList();
  }

  ///渲染首页的热门tab下的view
  _renderHotView() {
    return HotView();
  }

  ///渲染首页普通tab下的View
  _renderNormalView(categoryId) {
    return NormalView(categoryId);
  }
}
