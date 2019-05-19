import 'package:flutter/material.dart';
import 'package:toast/toast.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:mu_ying_mall/utils/jump.dart';

class CategoryManagerPage extends StatefulWidget {
  @override
  CategoryManagerState createState() {
    return new CategoryManagerState();
  }
}

class CategoryManagerState extends State<CategoryManagerPage> {
  List<dynamic> _categoryList = [];

  @override
  void initState() {
    super.initState();
    refreshData();
  }

  @override
  Widget build(BuildContext context) {
    return _buildScaffoldView(context);
  }

  ///构建基础视图
  Widget _buildScaffoldView(context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('分类管理'),
          backgroundColor: Colors.blue,
        ),
        body: Container(
          width: MediaQuery.of(context).size.width,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Expanded(
                child: ListView.builder(
                    itemCount: _categoryList.length,
                    itemBuilder: (context, index) => _buildItem(index)),
              ),
              Container(
                  height: 50,
                  width: MediaQuery.of(context).size.width,
                  color: Colors.red,
                  alignment: AlignmentDirectional.center,
                  child: GestureDetector(
                    child: Text(
                      "增加分类",
                      style: TextStyle(color: Colors.white, fontSize: 20),
                    ),
                    onTap: () => _jumpToEdit(null),
                  )),
            ],
          ),
        ));
  }

  _jumpToEdit(data) {
    jumpToEdit(context, "category", data, _onBackFromEdit);
  }

  //从编辑页回来
  _onBackFromEdit(needRefresh) {
    refreshData();
  }

  refreshData() {
    get("queryAllCategory", (categoryList) {
      setState(() {
        _categoryList = categoryList;
      });
    });
  }

  //buildItem
  _buildItem(int index) {
    var category = _categoryList[index];
    return Column(
      children: <Widget>[
        Row(
          children: <Widget>[
            Container(
              width: 20,
            ),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Container(
                    height: 10,
                  ),
                  Text(
                    category['categoryName'],
                    maxLines: 1,
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  Container(
                    height: 10,
                  ),
                ],
              ),
            ),
            GestureDetector(
              child: Icon(
                Icons.mode_edit,
                color: Colors.grey,
              ),
              onTap: () => _jumpToEdit(category),
            ),
            Container(
              width: 20,
            )
          ],
        ),
        Container(
          height: 0.1,
          width: MediaQuery.of(context).size.width,
          color: Colors.grey,
        )
      ],
    );
  }
}
