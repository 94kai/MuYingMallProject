import 'package:flutter/material.dart';
import '../view/shoppingcart/shopping_cart_view.dart';
class ShoppingCartPage extends StatefulWidget {
  @override
  ShoppingCartPageState createState() {
    return new ShoppingCartPageState();
  }
}

class ShoppingCartPageState extends State<ShoppingCartPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text("购物车"),
      ),
      body: ShoppingCartView(),
    );
  }
}
