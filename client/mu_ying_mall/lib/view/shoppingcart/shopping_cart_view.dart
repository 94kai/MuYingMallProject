import 'package:flutter/material.dart';
import '../common/product_list_view.dart';
import '../../utils/network.dart';
import '../../utils/jump.dart';
import '../../utils/login_util.dart';

class ShoppingCartView extends StatefulWidget {
  @override
  ShoppingCartViewState createState() {
    return new ShoppingCartViewState();
  }
}

class ShoppingCartViewState extends State<ShoppingCartView> {
  ///推荐商品列表
  var _productList = [];

  ///购物车商品列表
  var _cartProductList = [];
  var _isLogin = false;

  @override
  void initState() {
    super.initState();

    checkLoginState().then((isLogin) => _onLoginStateReturn(isLogin));

    get("queryProductsByCategory", (productList) {
      setState(() {
        _productList = productList;
      });
    }, params: {"categoryId": "-1"});
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      alignment: AlignmentDirectional.bottomCenter,
      children: <Widget>[
        ListView.builder(
            itemBuilder: (BuildContext context, int index) =>
                _buildItem(context, index)),
        _buildBottomView(),
      ],
    );
  }

  _buildItem(BuildContext context, int index) {
    if (index == 0) {
      return _buildShoppingCart();
    } else {
      return buildProductList(_productList, index - 1,
          (data) => jumpToProductDetail(context, data['id']));
    }
  }

  _buildShoppingCart() {
    return Column(
      children: <Widget>[
        _buildCoreView(),
        Container(
          height: 1,
          color: Colors.black12,
        ),
        Container(
          padding: const EdgeInsets.all(8.0),
          child: Text(
            '相似推荐',
            style: TextStyle(fontSize: 18),
          ),
        ),
      ],
    );
  }

  _buildCoreView() {
    return Container(
      color: Colors.white,
      child: _isLogin ? _buildLoginView() : _buildNoLoginView(),
    );
  }

  ///获取到登录态
  _onLoginStateReturn(bool isLogin) {
    if (isLogin) {
      setState(() {
        _isLogin = true;
      });
      getUserNameAndToken(
          (userName, token) => get("queryCartByUserName", (data) {
                print(data);
                setState(() {
                  print(data);
                  _cartProductList = data;
                });
              }, params: {"userName": userName}, headers: {"token": token}));
    } else {
      setState(() {
        _isLogin = false;
      });
    }
  }

  _buildNoLoginView() {
    return Container(
      padding: EdgeInsets.all(30),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Icon(
            Icons.remove_shopping_cart,
            size: 38,
            color: Colors.grey,
          ),
          Container(
            width: 20,
          ),
          Column(
            children: <Widget>[
              Text(
                '购物车空啦！！！',
                style: TextStyle(fontSize: 18, color: Colors.grey),
                textAlign: TextAlign.center,
                maxLines: 1,
              ),
              Row(
                children: <Widget>[
                  GestureDetector(
                    onTap: () =>
                        jumpToLogin(context, (result) => print("登录完了，刷购物车")),
                    child: Text(
                      '登录',
                      style: TextStyle(fontSize: 18, color: Colors.blue),
                      textAlign: TextAlign.center,
                      maxLines: 1,
                    ),
                  ),
                  Text(
                    '查看您的购物车。',
                    style: TextStyle(fontSize: 18, color: Colors.grey),
                    textAlign: TextAlign.center,
                    maxLines: 1,
                  ),
                ],
              ),
            ],
          ),
        ],
      ),
    );
  }

  _buildLoginView() {
    if (_cartProductList.length == 0) {
      return Container(
        padding: EdgeInsets.all(30),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Icon(
              Icons.remove_shopping_cart,
              size: 38,
              color: Colors.grey,
            ),
            Container(
              width: 20,
            ),
            Column(
              children: <Widget>[
                Text(
                  '购物车空啦！！！',
                  style: TextStyle(fontSize: 18, color: Colors.grey),
                  textAlign: TextAlign.center,
                  maxLines: 1,
                ),
              ],
            ),
          ],
        ),
      );
    } else {
      return Column(
        children: _buildCartProductItem(context),
      );
    }
  }

  ///构建底部view
  _buildBottomView() {
    if (_isLogin && _cartProductList.length > 0) {
      return Container(
          color: Colors.white,
          height: 50,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Container(),
              Row(
                children: <Widget>[
                  Text(
                    "合计：￥",
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  Text(
                    "100",
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                ],
              ),
              Container(
                alignment: AlignmentDirectional.center,
                height: 50,
                width: 150,
                child: Text(
                  "去结算",
                  style: TextStyle(color: Colors.white),
                ),
                color: Colors.pinkAccent,
              )
            ],
          ));
    } else {
      return Container();
    }
  }

  var checkState = {};

  ///构建加车的商品
  _buildCartProductItem(BuildContext context) {
    if (_cartProductList.length > 0) {
      var index = -1;
      return _cartProductList.map<Widget>((product) {
        index++;
        return Card(
          margin: EdgeInsets.all(10),
          child: Container(
            padding: EdgeInsets.all(10),
            height: 120,
            width: MediaQuery.of(context).size.width,
            child: Row(
              children: <Widget>[
                Checkbox(
                    value: checkState[index]==null?false:checkState[index],
                    onChanged: (checked) => setState(() {
                          checkState[index] = checked;
                        })),
                Image.network(
                  product['image'],
                  height: 90,
                  width: 90,
                ),
                Container(
                  width: 10,
                ),
                Expanded(
                  flex: 1,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Expanded(
                          flex: 1,
                          child: Text(
                            product['title'],
                            softWrap: true,
                            style: TextStyle(
                                fontSize: 18, fontWeight: FontWeight.bold),
                          )),
                      Expanded(
                        flex: 1,
                        child: Row(
                          children: <Widget>[
                            Text(
                              "￥",
                              style: TextStyle(
                                  color: Colors.red,
                                  fontSize: 10,
                                  fontWeight: FontWeight.bold),
                            ),
                            Text(
                              "${product['original_price']}",
                              style: TextStyle(
                                  color: Colors.red,
                                  fontSize: 18,
                                  fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      )
                    ],
                  ),
                )
              ],
            ),
          ),
        );
      }).toList();
    }
  }
}
