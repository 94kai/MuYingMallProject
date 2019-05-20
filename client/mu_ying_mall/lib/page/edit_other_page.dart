import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:mu_ying_mall/utils/login_util.dart';
import 'package:mu_ying_mall/utils/jump.dart';
import 'package:toast/toast.dart';

//增加、编辑 分类、资讯、活动页
class EditOtherPage extends StatefulWidget {
  String from;
  dynamic data;

  EditOtherPage(this.from, this.data);

  @override
  EditOtherPageState createState() {
    return EditOtherPageState();
  }
}

class EditOtherPageState extends State<EditOtherPage> {
  final TextEditingController _controller1 = new TextEditingController();

  var _focusNode1 = FocusNode();

  var tips = new TextStyle(fontSize: 18.0, color: Colors.black);

  @override
  void initState() {
    super.initState();
    switch (widget.from) {
      case "category":
        field = "categoryName";
        break;
      case "news":
        field = "news";
        break;
      case "promotion":
        field = "title";
        break;
    }
    _controller1.text = widget.data == null ? "" : widget.data['${field}'];
  }

  @override
  void dispose() {
    super.dispose();
  }

  var title;
  var prefix;
  var suffix;
  var field;

  @override
  Widget build(BuildContext context) {
    prefix = widget.data == null ? "添加" : "编辑";
    switch (widget.from) {
      case "category":
        suffix = "分类";
        break;
      case "news":
        suffix = "资讯";
        break;
      case "promotion":
        suffix = "活动";
        break;
    }
    title = prefix + suffix;

    return Scaffold(
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
                child: Column(
                  children: <Widget>[
                    _renderItem("${suffix}", '请填写${suffix}'),
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
                    onTap: () => saveData(),
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
    print(widget.data);
    print(widget.from);
    var functionId;
    var field;
    var fieldValue;
    switch (widget.from) {
      case "category":
        functionId = "deleteCategory";
        field = "id";
        fieldValue = "categoryId";
        break;
      case "news":
        functionId = "deleteNews";
        field = "id";
        fieldValue = "id";
        break;
      case "promotion":
        functionId = "deletePromotion";
        field = "promotionId";
        fieldValue = "pomotionId";
        break;
    }
    get(functionId, (response) => Navigator.of(context).pop(true),
        params: {
          field: "${widget.data["${fieldValue}"]}",
        },
        errorCallback: (msg) => Toast.show("删除失败", context));
  }

  _renderItem(String name, String hint) {
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
                      focusNode: _focusNode1,
                      style: tips,
                      controller: _controller1,
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

  //保存
  saveData() {
    if (_controller1.text.isEmpty) {
      Toast.show("请完善信息", context);
      return;
    }
    print(widget.data);
    var functionIdAdd;
    var functionIdUpdate;
    var field;
    var fieldId;
    var fieldIdName;
    var fieldValue;
    switch (widget.from) {
      case "category":
        functionIdAdd = "addCategory";
        functionIdUpdate = "updateCategory";
        field = "name";
        fieldId = "id";
        fieldIdName = "categoryId";
        fieldValue = "name";
        break;
      case "news":
        functionIdAdd = "addNews";
        functionIdUpdate = "updateNews";
        field = "news";
        fieldId = "id";
        fieldIdName = "id";
        fieldValue = "news";
        break;
      case "promotion":
        functionIdAdd = "addPromotion";
        functionIdUpdate = "updatePromotion";
        field = "title";
        fieldId = "promotionId";
        fieldIdName = "pomotionId";
        fieldValue = "title";
        break;
    }
    if (widget.data == null) {
      //add
      get(functionIdAdd, (response) => Navigator.of(context).pop(true),
          params: {
            field: _controller1.text,
          },
          errorCallback: (msg) => Toast.show("添加失败", context));
    } else {
      //update
      get(functionIdUpdate, (response) => Navigator.of(context).pop(true),
          params: {
            fieldId: "${widget.data["${fieldIdName}"]}",
            fieldValue: _controller1.text,
          },
          errorCallback: (msg) => Toast.show("更新失败", context));
    }
  }
}
