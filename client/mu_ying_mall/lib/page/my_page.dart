import 'package:flutter/material.dart';
import '../utils/login_util.dart';
import '../utils/jump.dart';
import 'package:toast/toast.dart';

class MyPage extends StatefulWidget {
  @override
  MyPagePageState createState() {
    return new MyPagePageState();
  }
}

class MyPagePageState extends State<MyPage> {
  var headText = '注册/登录';
  bool isLogin = false;

  @override
  void initState() {
    super.initState();
    _refreshLoginState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView(
        children: <Widget>[
          Stack(children: [
            Image.asset(
              "images/bg.jpeg",
              width: MediaQuery.of(context).size.width,
              height: 180,
              fit: BoxFit.fill,
            ),
            _buildHeadView(),
            Positioned(
              bottom: -1.0,
              child: Container(
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(15.0),
                      topRight: Radius.circular(15.0),
                    ),
                    color: Colors.white),
                width: MediaQuery.of(context).size.width,
                height: 20,
              ),
            ),
          ]),
          GestureDetector(
              onTap: () => Toast.show("我的收藏", context),
              child: Container(
                width: MediaQuery.of(context).size.width,
                color: Colors.white,
                padding: EdgeInsets.all(10),
                child: Row(
                  children: <Widget>[
                    Container(
                      width: 10,
                    ),
                    Icon(Icons.favorite),
                    Container(
                      width: 10,
                    ),
                    Expanded(
                      flex: 1,
                      child: Text(
                        "我的收藏",
                        style: TextStyle(fontSize: 18),
                      ),
                    ),
                    Icon(Icons.arrow_forward_ios),
                  ],
                ),
              )),
          GestureDetector(
              onTap: () => Toast.show("意见反馈", context),
              child: Container(
                width: MediaQuery.of(context).size.width,
                color: Colors.white,
                padding: EdgeInsets.all(10),
                child: Row(
                  children: <Widget>[
                    Container(
                      width: 10,
                    ),
                    Icon(Icons.feedback),
                    Container(
                      width: 10,
                    ),
                    Expanded(
                      flex: 1,
                      child: Text(
                        "意见反馈",
                        style: TextStyle(fontSize: 18),
                      ),
                    ),
                    Icon(Icons.arrow_forward_ios),
                  ],
                ),
              )),
          GestureDetector(
              onTap: () => Toast.show("已是最新版本", context),
              child: Container(
                width: MediaQuery.of(context).size.width,
                color: Colors.white,
                padding: EdgeInsets.all(10),
                child: Row(
                  children: <Widget>[
                    Container(
                      width: 10,
                    ),
                    Icon(Icons.update),
                    Container(
                      width: 10,
                    ),
                    Expanded(
                      flex: 1,
                      child: Text(
                        "版本更新",
                        style: TextStyle(fontSize: 18),
                      ),
                    ),
                    Icon(Icons.arrow_forward_ios),
                  ],
                ),
              )),
          GestureDetector(
              onTap: () => Toast.show("关于", context),
              child: Container(
                margin: EdgeInsets.only(top: 20),
                width: MediaQuery.of(context).size.width,
                color: Colors.white,
                padding: EdgeInsets.all(10),
                child: Row(
                  children: <Widget>[
                    Container(
                      width: 10,
                    ),
                    Icon(Icons.info),
                    Container(
                      width: 10,
                    ),
                    Expanded(
                      flex: 1,
                      child: Text(
                        "关于",
                        style: TextStyle(fontSize: 18),
                      ),
                    ),
                    Icon(Icons.arrow_forward_ios),
                  ],
                ),
              )),
          _buildLogout(),
        ],
      ),
    );
  }

  _buildHeadView() {
    return GestureDetector(
        child: Container(
          margin: EdgeInsets.only(left: 20, top: 40),
          child: Row(
            children: <Widget>[
              ClipOval(
                child: Image.asset(
                  'images/icon.png',
                  width: 70,
                  height: 70,
                  fit: BoxFit.fill,
                ),
              ),
              Container(
                width: 10,
              ),
              Text(
                headText,
                style: TextStyle(color: Colors.white, fontSize: 23),
              ),
            ],
          ),
        ),
        onTap: () {
          if (!isLogin) {
            jumpToLogin(context, (result) {
              if (result != null && result) {
                _refreshLoginState();
              }
            });
          }
        });
  }

  _refreshLoginState() {
    getUserNameAndToken((userName, token) {
      setState(() {
        if (token != null && token.length > 0) {
          headText = userName;
          isLogin = true;
        } else {
          headText = '注册/登录';
          isLogin = false;
        }
      });
    });
  }

  _buildLogout() {
    return isLogin
        ? GestureDetector(
            onTap: () {
              logout();
              _refreshLoginState();
            },
            child: Container(
              margin: EdgeInsets.only(top: 20),
              width: MediaQuery.of(context).size.width,
              color: Colors.white,
              padding: EdgeInsets.all(10),
              child: Center(
                child: Text(
                  "退出登录",
                  style: TextStyle(fontSize: 18),
                ),
              ),
            ))
        : Container();
  }
}
