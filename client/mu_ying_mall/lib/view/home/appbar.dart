import 'package:flutter/material.dart';

//渲染tabbar（appbar下面的）
renderTopTabBar(tabController, categorys, onTopTabBarClick) {
  return TabBar(
    labelColor: Colors.white,
    unselectedLabelColor: Colors.white70,
    labelStyle: TextStyle(fontWeight: FontWeight.bold),
    controller: tabController,
    isScrollable: true,
    unselectedLabelStyle: TextStyle(fontWeight: FontWeight.bold),
    tabs: _renderCategoryTab(categorys),
    onTap: (index) => onTopTabBarClick(index),
  );
}

//渲染分类tab
List<Widget> _renderCategoryTab(List<dynamic> categorys) {
  if (categorys.length == 0) {
    return [
      Tab(
        child: Text(""),
      ),
    ];
  }
  return categorys.map((category) {
    String tabTitle =
        (category['categoryName'] == null) ? "" : category['categoryName'];
    return Tab(text: tabTitle);
  }).toList();
}

//渲染首页的title
renderTitleView() {
  return Container(
      height: kToolbarHeight,
      margin: EdgeInsets.fromLTRB(5, 10, 5, 0),
      child: Row(
        children: <Widget>[
          Expanded(
            flex: 1,
            child: Material(
              color: Colors.white70,
              borderRadius: BorderRadius.all(Radius.circular(10)),
              child: Row(
                children: <Widget>[
                  Padding(
                    padding: EdgeInsets.fromLTRB(5, 10, 0, 5),
                    child: Icon(
                      Icons.search,
                      color: Colors.grey,
                    ),
                  ),
                  Expanded(
                    flex: 1,
                    child: Text(
                      "请输入搜索关键词",
                      style: TextStyle(color: Colors.grey),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(right: 5),
                    child: Icon(
                      Icons.photo_camera,
                      color: Colors.grey,
                    ),
                  ),
                ],
              ),
            ),
          ),
          IconButton(
            onPressed: () => {},
            icon: Icon(
              Icons.person,
            ),
            padding: const EdgeInsets.all(1.0),
          ),
        ],
      ));
}
