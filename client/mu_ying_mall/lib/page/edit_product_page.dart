import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:mu_ying_mall/utils/login_util.dart';
import 'package:mu_ying_mall/utils/jump.dart';
import 'package:toast/toast.dart';
import 'package:flutter_picker/flutter_picker.dart';
import 'dart:convert';

//增加、编辑 商品页
class EditProductPage extends StatefulWidget {
  dynamic data;

  EditProductPage(this.data);

  @override
  EditProductPageState createState() {
    return EditProductPageState();
  }
}

class EditProductPageState extends State<EditProductPage> {
  final TextEditingController _controller1 = new TextEditingController();
  final TextEditingController _controller2 = new TextEditingController();
  final TextEditingController _controller3 = new TextEditingController();
  List<TextEditingController> controllers = [];

  var _focusNode1 = FocusNode();
  var _focusNode2 = FocusNode();
  var _focusNode3 = FocusNode();

  //分类、活动的文本
  List selectTexts = ["", ""];

  //分类、活动的id
  List selectIds = ["", ""];
  List focusNodes = [];

  var tips = new TextStyle(fontSize: 18.0, color: Colors.black);
  var hintTips = new TextStyle(fontSize: 18.0, color: Colors.black26);

  @override
  void initState() {
    super.initState();
    controllers.add(_controller1);
    controllers.add(_controller2);
    controllers.add(_controller3);
    focusNodes.add(_focusNode1);
    focusNodes.add(_focusNode2);
    focusNodes.add(_focusNode3);
    _controller1.text = widget.data == null ? "" : widget.data['title'];
    _controller2.text =
        widget.data == null ? "" : "${widget.data['original_price']}";
    _controller3.text = widget.data == null ? "" : widget.data['image'];
  }

  @override
  void dispose() {
    super.dispose();
  }

  var title;
  var prefix;
  var suffix;
  var field;
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    prefix = widget.data == null ? "添加" : "编辑";
    suffix = "商品";
    title = prefix + suffix;

    return Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: Text(title),
          actions: <Widget>[
            widget.data != null
                ? GestureDetector(
                    onTap: () => _deleteItem(),
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
                child: ListView(
                  children: <Widget>[
                    _renderItem("商品名称", '请填写商品名称', 0),
                    _renderItem("商品价格", '请填写商品价格', 1),
                    _renderItem("商品图片", '请填写商品图片url', 2),
                    _renderSelectItem("所属活动", '请选择所属活动', 0),
                    _renderSelectItem("所属分类", '请选择所属分类', 1),
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
                    onTap: () => saveProduct(),
                  )),
            ],
          ),
        ));
  }

  //删除条目
  _deleteItem() {
    if (widget.data == null) {
      Toast.show("删除失败", context);
      return;
    }
    get("deleteProduct", (response) => Navigator.of(context).pop(true),
        params: {
          "productId": "${widget.data['id']}",
        },
        errorCallback: () => Toast.show("删除失败", context));
  }

  _renderItem(String name, String hint, int index) {
    var focusNode = focusNodes[index];
    var controller = controllers[index];
    return Container(
        height: 70,
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
                      focusNode: focusNode,
                      style: tips,
                      controller: controller,
                      decoration: new InputDecoration(
                        hintText: hint,
                        border: InputBorder.none,
                        hintStyle:
                            TextStyle(fontSize: 18, color: Colors.black26),
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

  _renderSelectItem(String name, String hint, int index) {
    var id = selectIds[index];
    var text = selectTexts[index];
    var tipsStyle;
    print(text);
    if (text == "") {
      tipsStyle = hintTips;
    } else {
      hint = text;
      tipsStyle = tips;
    }
    return Container(
        height: 70,
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
                      child: GestureDetector(
                    onTap: () => _showSelector(index),
                    child: Text(
                      hint,
                      style: tipsStyle,
                    ),
                  )),
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

  //保存
  saveProduct() {
    for (var value in controllers) {
      if (value.text.isEmpty) {
        Toast.show("请完善信息", context);
        return;
      }
    }
    for (var value in selectIds) {
      if (value.isEmpty) {
        Toast.show("请完善信息", context);
        return;
      }
    }
    if (widget.data == null) {
      //add
      post("appProduct", (response) => Navigator.of(context).pop(true),
          params: {
            "title": controllers[0].text,
            "original_price": controllers[1].text,
            "image": controllers[2].text,
            "promotionId": selectIds[0],
            "categoryId": selectIds[1],
          },
          errorCallback: (msg) => Toast.show("添加商品失败${msg}", context));
    } else {
      print(widget.data);
      //update
      post("updateProduct", (response) => Navigator.of(context).pop(true),
          params: {
            "id": "${widget.data['id']}",
            "title": controllers[0].text,
            "original_price": controllers[1].text,
            "image": controllers[2].text,
            "promotionId": selectIds[0],
            "categoryId": selectIds[1],
          },
          errorCallback: (msg) => Toast.show("更新商品失败${msg}", context));
    }
  }

  List promotionList = [];
  List categoryList = [];

  _showSelector(index) {
    if (index == 0) {
      get("queryAllHotPromotion", (newsAndPromotion) {
        setState(() {
          promotionList = newsAndPromotion["promotionList"];
        });
      });
    } else {
      get("queryAllCategory", (mCategoryList) {
        setState(() {
          categoryList = mCategoryList;
        });
      });
    }

    showModalBottomSheet(
        context: context, //state.context,
        builder: (BuildContext context) {
          return _buildSelectList(
              index,
              index == 0 ? promotionList : categoryList,
              index == 0 ? "title" : "categoryName",
              index == 0 ? "pomotionId" : "categoryId",
              index == 0 ? "请选择活动" : "请选择分类");
        });
  }

  _buildSelectList(_index, data, field1, field2, title) {
    return Column(
      children: <Widget>[
        Container(
          height: 10,
        ),
        Text(
          title,
          style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        ),
        Expanded(
          child: ListView.builder(
            itemBuilder: (context, index) => Padding(
                padding: EdgeInsets.all(10),
                child: Center(
                    child: GestureDetector(
                        onTap: () => _select(
                            data[index][field1], data[index][field2], _index),
                        child: Text(
                          data[index][field1],
                          style: TextStyle(fontSize: 18),
                        )))),
            itemCount: data.length,
          ),
        ),
      ],
    );
  }

  _select(title, id, index) {
    Navigator.of(context).pop();
    selectIds[index] = "${id}";
    selectTexts[index] = "${title}";
    setState(() {});
  }
}
