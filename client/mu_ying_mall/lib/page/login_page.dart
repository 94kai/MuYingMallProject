import 'package:flutter/material.dart';
import '../utils/login_util.dart';
import 'package:toast/toast.dart';

class LoginPage extends StatefulWidget {
  @override
  LoginPageState createState() {
    return new LoginPageState();
  }
}

class LoginPageState extends State<LoginPage> {
  var isLogin = true;

  var title;
  var btnText;
  var tipPrefix;
  var actionTip;
  var userNameHint;
  var passWordHint;
  var confirmPassWordHint;

  @override
  Widget build(BuildContext context) {
    title = isLogin ? "登录" : "注册";
    btnText = isLogin ? "马上登录" : "注册";
    tipPrefix = isLogin ? "还没有账号?" : "已有账号?";
    actionTip = isLogin ? "立即注册" : "去登陆";
    userNameHint = isLogin ? "请输入用户名" : "用户名";
    passWordHint = isLogin ? "请输入密码" : "密码";
    confirmPassWordHint = "确认密码";
    return _buildScaffoldView(context);
  }

  ///构建基础视图
  Widget _buildScaffoldView(context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: _buildContentView(context),
    );
  }

  var leftRightPadding = 30.0;
  var topBottomPadding = 4.0;
  var textTips = new TextStyle(fontSize: 16.0, color: Colors.black);
  var hintTips = new TextStyle(fontSize: 15.0, color: Colors.black26);

  var _userNameController = new TextEditingController();
  var _userPassController = new TextEditingController();
  var _confirmPserNameController = new TextEditingController();

  var _focusNode1 = FocusNode();
  var _focusNode2 = FocusNode();
  var _focusNode3 = FocusNode();

  _buildContentView(context) {
    return ListView(
      children: <Widget>[
        new Padding(
          padding: new EdgeInsets.fromLTRB(0, 50.0, 0, 10.0),
          child: Image.asset(
            "images/icon.png",
            width: 80,
            height: 80,
          ),
        ),
        new Padding(
          padding: new EdgeInsets.fromLTRB(
              leftRightPadding, 20.0, leftRightPadding, topBottomPadding),
          child: new TextField(
            focusNode: _focusNode1,
            style: hintTips,
            controller: _userNameController,
            decoration: new InputDecoration(hintText: userNameHint),
            obscureText: false,
          ),
        ),
        new Padding(
          padding: new EdgeInsets.fromLTRB(
              leftRightPadding, 10.0, leftRightPadding, topBottomPadding),
          child: new TextField(
            focusNode: _focusNode2,
            style: hintTips,
            controller: _userPassController,
            decoration: new InputDecoration(hintText: passWordHint),
            obscureText: true,
          ),
        ),
        !isLogin
            ? new Padding(
                padding: new EdgeInsets.fromLTRB(
                    leftRightPadding, 10.0, leftRightPadding, topBottomPadding),
                child: new TextField(
                  focusNode: _focusNode3,
                  style: hintTips,
                  controller: _confirmPserNameController,
                  decoration:
                      new InputDecoration(hintText: confirmPassWordHint),
                  obscureText: true,
                ),
              )
            : Container(),
        new Container(
          width: MediaQuery.of(context).size.width,
          padding: new EdgeInsets.fromLTRB(
              leftRightPadding - 4, 20.0, leftRightPadding - 4, 0),
          child: new Card(
              color: Colors.pink,
              child: new FlatButton(
                onPressed: () {
                  _toDo(isLogin);
                },
                child: new Text(
                  btnText,
                  style: new TextStyle(color: Colors.white, fontSize: 16.0),
                ),
              )),
        ),
        Padding(
            padding: EdgeInsets.fromLTRB(leftRightPadding, 5, 0, 0),
            child: Row(
              children: <Widget>[
                Text('$tipPrefix'),
                GestureDetector(
                  child: Text(
                    actionTip,
                    style: TextStyle(color: Colors.blue),
                  ),
                  onTap: () {
                    setState(() {
                      isLogin = !isLogin;
                      _userNameController.clear();
                      _userPassController.clear();
                      _confirmPserNameController.clear();
                    });
                  },
                ),
              ],
            )),
      ],
    );
  }

  ///进行登录或者注册
  _toDo(bool isLogin) {
    _focusNode1.unfocus();
    _focusNode2.unfocus();
    _focusNode3.unfocus();
    if (isLogin) {
      if ((_userNameController.text.isNotEmpty &&
          _userPassController.text.isNotEmpty)) {
        login(_userNameController.text, _userPassController.text, (data) {
          Navigator.of(context).pop(true);
        }, (error) {
          Toast.show("$error", context);
        });
      } else {
        Toast.show("用户名或密码不能为空", context);
      }
    } else {
      if ((_userNameController.text.isEmpty ||
          _userPassController.text.isEmpty ||
          _confirmPserNameController.text.isEmpty)) {
        Toast.show("用户名或密码不能为空", context);
      } else if (_userPassController.text != _confirmPserNameController.text) {
        Toast.show("两次输入的密码不一致", context);
      } else {
        register(_userNameController.text, _userPassController.text, (data) {
          Navigator.of(context).pop(true);
        }, (error) {
          Toast.show("$error", context);
        });
      }
    }
  }
}
