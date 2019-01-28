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
  var _productList = [];
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
    return ListView.builder(
        itemBuilder: (BuildContext context, int index) =>
            _buildItem(context, index));
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

      getToken().then((token) =>
          get("queryShoppingCartList", (data) {
            setState(() {
              //TODO:请求购物车数据
              _isLogin = true;
            });
          }, params: {"token": token}));
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

  _buildLoginView() {}
}
