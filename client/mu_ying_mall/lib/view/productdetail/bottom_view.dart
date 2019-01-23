import 'package:flutter/material.dart';
import '../../utils/jump.dart';
class BottomView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: _renderBottomTab([
        {
          'icon': Icons.chat,
          'title': '客服',
          'flex': 3,
          'click': () => print("暂未实现")
        },
        {
          'icon': Icons.shopping_cart,
          'title': '购物车',
          'flex': 3,
          'click': () => jumpToShoppingCart(context)
        },
        {'title': '加入购物车', 'flex': 4, 'click': () => print("加购物车")},
      ]),
    );
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
