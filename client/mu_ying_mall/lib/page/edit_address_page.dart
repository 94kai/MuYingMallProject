import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:mu_ying_mall/utils/login_util.dart';
import 'package:mu_ying_mall/utils/jump.dart';
import 'package:toast/toast.dart';

//增加地址、编辑地址页
class EditAddressPage extends StatefulWidget {
  dynamic address;
  String userName;

  EditAddressPage(this.address, this.userName);

  @override
  EditAddressPageState createState() {
    return EditAddressPageState();
  }
}

class EditAddressPageState extends State<EditAddressPage> {
  final TextEditingController _controller1 = new TextEditingController();
  final TextEditingController _controller2 = new TextEditingController();
  final TextEditingController _controller3 = new TextEditingController();
  List<TextEditingController> controllers = [];

  var _focusNode1 = FocusNode();
  var _focusNode2 = FocusNode();
  var _focusNode3 = FocusNode();
  List focusNodes = [];

  var hintTips = new TextStyle(fontSize: 20.0, color: Colors.black);

  @override
  void initState() {
    super.initState();
    print(widget.address);
    controllers.add(_controller1);
    controllers.add(_controller2);
    controllers.add(_controller3);
    focusNodes.add(_focusNode1);
    focusNodes.add(_focusNode2);
    focusNodes.add(_focusNode3);
    _controller1.text =
        widget.address == null ? "" : widget.address['consignee'];
    _controller2.text =
        widget.address == null ? "" : widget.address['phoneNumber'];
    _controller3.text = widget.address == null ? "" : widget.address['address'];
//    getUserName()
//        .then((userName) => get("queryAllAddressByUserName", (addressList) {
//              setState(() {
//                _addressList = addressList;
//              });
//            }, params: {"userName": userName}));
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.address == null ? '添加收货地址' : '编辑收货地址'),
          actions: <Widget>[
            widget.address != null
                ? GestureDetector(
                    onTap: () => _deleteAddress(),
                    child: Container(
                      padding: EdgeInsets.only(right: 20),
                      alignment: AlignmentDirectional.center,
                      child: Text('删除'),
                    ),
                  )
                : Container(),
          ],
        ),
        body: Container(
          width: MediaQuery.of(context).size.width,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Expanded(
                child: Column(
                  children: <Widget>[
                    _renderItem('收货人', '请填写收货人', 0),
                    _renderItem('手机号码', '请填写收货人手机号码', 1),
                    _renderAddress('收货地址', '请填写收货人地址', 2),
                  ],
                ),
              ),
              Container(
                  height: 50,
                  width: MediaQuery.of(context).size.width,
                  color: Colors.red,
                  alignment: AlignmentDirectional.center,
                  child: GestureDetector(
                    child: Text(
                      "保存",
                      style: TextStyle(color: Colors.white, fontSize: 20),
                    ),
                    onTap: () => saveAddress(),
                  )),
            ],
          ),
        ));
  }

  //删除地址
  _deleteAddress() {
    if (widget.address == null) {
      Toast.show("删除失败", context);
      return;
    }
    get("deleteAddress", (response) => Navigator.of(context).pop(true),
        params: {
          "addressId": "${widget.address['id']}",
        },
        errorCallback: () => Toast.show("删除失败", context));
  }

  _renderItem(String name, String hint, int index) {
    return Container(
        height: 80,
        child: Column(
          children: <Widget>[
            Expanded(
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Container(
                    width: 25,
                  ),
                  Container(
                    width: 90,
                    child: Text(
                      name,
                      style: TextStyle(fontSize: 20),
                    ),
                  ),
                  Expanded(
                    child: TextField(
                      maxLines: index == 2 ? 10 : 1,
                      focusNode: focusNodes[index],
                      style: hintTips,
                      controller: controllers[index],
                      decoration: new InputDecoration(
                        hintText: hint,
                        border: InputBorder.none,
                        hintStyle:
                            TextStyle(fontSize: 20, color: Colors.black26),
                      ),
                      obscureText: false,
                    ),
                  ),
                  Container(
                    width: 25,
                  ),
                ],
              ),
            ),
            Container(
              margin: EdgeInsets.only(left: 25, right: 25),
              height: 1,
              color: Colors.black12,
            )
          ],
        ));
  }

  _renderAddress(String name, String hint, int index) {
    return Expanded(
        child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
          Container(
            width: 25,
          ),
          Container(
            padding: EdgeInsets.only(top: 13),
            width: 90,
            child: Text(
              name,
              style: TextStyle(fontSize: 20),
            ),
          ),
          Expanded(
            child: TextField(
              maxLines: index == 2 ? 10 : 1,
              focusNode: focusNodes[index],
              style: hintTips,
              controller: controllers[index],
              decoration: new InputDecoration(
                hintText: hint,
                border: InputBorder.none,
                hintStyle: TextStyle(fontSize: 20, color: Colors.black26),
              ),
              obscureText: false,
            ),
          ),
          Container(
            width: 25,
          ),
        ]));
  }

  //保存地址
  saveAddress() {
    if (controllers[0].text.isEmpty ||
        controllers[1].text.isEmpty ||
        controllers[2].text.isEmpty) {
      Toast.show("请完善信息", context);
      return;
    }
    if (widget.address == null) {
      //add
      get("addAddress", (response) => Navigator.of(context).pop(true),
          params: {
            "consignee": controllers[0].text,
            "phoneNumber": controllers[1].text,
            "address": controllers[2].text,
            "userName": widget.userName,
          },
          errorCallback: () => Toast.show("添加地址失败", context));
    } else {
      //update
      get("updateAddress", (response) => Navigator.of(context).pop(true),
          params: {
            "consignee": controllers[0].text,
            "phoneNumber": controllers[1].text,
            "address": controllers[2].text,
            "addressId": "${widget.address['id']}",
          },
          errorCallback: () => Toast.show("更新地址失败", context));
    }
  }
}
