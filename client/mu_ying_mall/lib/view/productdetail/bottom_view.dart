import 'package:flutter/material.dart';
import '../../utils/jump.dart';
import 'package:toast/toast.dart';
import '../../utils/login_util.dart';
import '../../utils/network.dart';

class BottomView extends StatelessWidget {
  var productId;

  BottomView(this.productId);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: _renderBottomTab([
        {
          'icon': Icons.chat,
          'title': '客服',
          'flex': 3,
          'click': () => Toast.show('暂未实现', context,
              duration: Toast.LENGTH_SHORT, gravity: Toast.BOTTOM)
        },
        {
          'icon': Icons.shopping_cart,
          'title': '购物车',
          'flex': 3,
          'click': () => jumpToShoppingCart(context)
        },
        {
          'title': '加入购物车',
          'flex': 4,
          'click': () => _checkAddToShoppingCart(context)
        },
      ]),
    );
  }

  ///加车前的登录校验
  _checkAddToShoppingCart(context) {
    checkLoginState().then((hasToken) {
      if (hasToken) {
        ///加车
        _addToShoppingCart(context);
      } else {
        jumpToLogin(context, (result) {
          ///加车
          _addToShoppingCart(context);
        });
      }
    });
  }

  ///加车操作
  void _addToShoppingCart(context) {
    getUserNameAndToken((userName, token) => get(
        "addProductToCart", (data) => Toast.show("加入购物车成功", context),
        params: {'userName': userName, 'productId': '$productId'},
        headers: {'token': token}));
  }
}

//渲染底部tab
List<Widget> _renderBottomTab(List<dynamic> bottomTabBarData) {
  return bottomTabBarData.map<Widget>((data) {
    if (data['icon'] == null) {
      return Expanded(
        flex: data['flex'],
        child: GestureDetector(
            onTap: data['click'],
            child: Container(
              alignment: AlignmentDirectional.center,
              height: kTextTabBarHeight,
              color: Colors.pink,
              child: Text(
                data['title'],
                style:
                    TextStyle(fontWeight: FontWeight.bold, color: Colors.white),
              ),
            )),
      );
    }
    return Expanded(
        flex: data['flex'],
        child: GestureDetector(
          onTap: data['click'],
          child: Tab(
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
                  ))),
        ));
  }).toList();
}
