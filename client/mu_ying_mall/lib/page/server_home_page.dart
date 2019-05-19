import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/jump.dart';

//主页面主架构 底部tab
class ServerHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: Text("母婴商城后台管理系统"),
        ),
        body: new Center(
            child: new Container(
          padding: EdgeInsets.all(30),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              GestureDetector(
                onTap: () => _jumpToProduct(context),
                child: Column(
                  children: <Widget>[
                    ClipOval(
                      clipBehavior: Clip.hardEdge,
                      child: Container(
                        color: Colors.blue,
                        width: 50,
                        height: 50,
                        child: Center(
                          child: Image.asset(
                            'images/product.png',
                            width: 40,
                            height: 40,
                          ),
                        ),
                      ),
                    ),
                    Container(
                      height: 10,
                    ),
                    Text("商品管理")
                  ],
                ),
              ),
              GestureDetector(
                  onTap: () => _jumpToCategory(context),
                  child: Column(
                    children: <Widget>[
                      ClipOval(
                        clipBehavior: Clip.hardEdge,
                        child: Container(
                          color: Colors.blue,
                          width: 50,
                          height: 50,
                          child: Center(
                            child: Image.asset(
                              'images/category.png',
                              width: 25,
                              height: 25,
                            ),
                          ),
                        ),
                      ),
                      Container(
                        height: 10,
                      ),
                      Text("分类管理")
                    ],
                  )),
              GestureDetector(
                  onTap: () => _jumpToNews(context),
                  child: Column(
                    children: <Widget>[
                      ClipOval(
                        clipBehavior: Clip.hardEdge,
                        child: Container(
                          color: Colors.blue,
                          width: 50,
                          height: 50,
                          child: Center(
                            child: Image.asset(
                              'images/news.png',
                              width: 25,
                              height: 25,
                            ),
                          ),
                        ),
                      ),
                      Container(
                        height: 10,
                      ),
                      Text("资讯管理")
                    ],
                  )),
              GestureDetector(
                  onTap: () => _jumpToPromotion(context),
                  child: Column(
                    children: <Widget>[
                      ClipOval(
                        clipBehavior: Clip.hardEdge,
                        child: Container(
                          color: Colors.blue,
                          width: 50,
                          height: 50,
                          child: Center(
                            child: Image.asset(
                              'images/huodong.png',
                              width: 30,
                              height: 30,
                            ),
                          ),
                        ),
                      ),
                      Container(
                        height: 10,
                      ),
                      Text("活动管理")
                    ],
                  )),
            ],
          ),
        )));
  }

  _jumpToProduct(context) {
    jumpToProductManager(context);
  }

  _jumpToCategory(context) {
    jumpToCategoryManager(context);
  }

  _jumpToNews(context) {
    jumpToNewsManager(context);
  }

  _jumpToPromotion(context) {
    jumpToPromotionManager(context);
  }
}
