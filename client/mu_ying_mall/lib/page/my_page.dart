import 'package:flutter/material.dart';

class MyPage extends StatefulWidget {
  @override
  MyPagePageState createState() {
    return new MyPagePageState();
  }
}

class MyPagePageState extends State<MyPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      color: Colors.indigo,
      child: Center(
        child: Text("mypage"),
      ),
    );
  }
}
