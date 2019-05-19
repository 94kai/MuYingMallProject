import 'package:flutter/material.dart';
import 'package:mu_ying_mall/view/home/appbar.dart';
import '../utils/network.dart';
import '../view/home/hot_view.dart';
import '../view/home/normal_view.dart';
import '../utils/jump.dart';

class FunctionChoosePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      color: Colors.blue,
      child: Column(
        children: <Widget>[
          Container(
            height: 150,
          ),
          SizedBox(
              width: 250,
              height: 50,
              child: FlatButton(
                onPressed: () => _jumpToClient(context),
                child: Text("客户端 >",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        decoration: TextDecoration.none)),
                textColor: Colors.black,
                shape: RoundedRectangleBorder(
                    side: BorderSide(
                      color: Colors.white,
                      width: 1,
                    ),
                    borderRadius: BorderRadius.circular(8)),
              )),
          Container(
            height: 30,
          ),
          SizedBox(
              width: 250,
              height: 50,
              child: FlatButton(
                onPressed: () => _jumpToServer(context),
                child: Text("后台管理系统 >",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        decoration: TextDecoration.none)),
                textColor: Colors.black,
                shape: RoundedRectangleBorder(
                    side: BorderSide(
                      color: Colors.white,
                      width: 1,
                    ),
                    borderRadius: BorderRadius.circular(8)),
              )),
        ],
      ),
    ));
  }

  void _jumpToClient(context) {
    jumpToClient(context);
  }

  void _jumpToServer(context) {
    jumpToServer(context);
  }
}
