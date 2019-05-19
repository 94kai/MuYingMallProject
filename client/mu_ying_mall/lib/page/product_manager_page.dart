import 'package:flutter/material.dart';
import 'package:toast/toast.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:mu_ying_mall/utils/jump.dart';

class ProductManagerPage extends StatefulWidget {
  @override
  ProductManagerState createState() {
    return new ProductManagerState();
  }
}

class ProductManagerState extends State<ProductManagerPage> {
  List<dynamic> _productList = [];
  var _newsAndPromotion = {};

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
          title: Text('商品管理'),
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
                    itemCount: _productList.length,
                    itemBuilder: (context, index) => _buildItem(index)),
              ),
              Container(
                  height: 50,
                  width: MediaQuery.of(context).size.width,
                  color: Colors.red,
                  alignment: AlignmentDirectional.center,
                  child: GestureDetector(
                    child: Text(
                      "增加商品",
                      style: TextStyle(color: Colors.white, fontSize: 20),
                    ),
                    onTap: () =>
                        jumpToEditCategory(context, null, _onBackFromEdit),
                  )),
            ],
          ),
        ));
  }

  //从编辑页回来
  _onBackFromEdit(needRefresh) {
    refreshData();
  }

  refreshData() {
    get("queryProductsByCategory", (productList) {
      setState(() {
        _productList = productList;
      });
    }, params: {"categoryId": "-1"});
  }

  //buildItem
  _buildItem(int index) {
    var product = _productList[index];
    return Column(
      children: <Widget>[
        Container(
          height: 10,
        ),
        Row(
          children: <Widget>[
            Container(
              width: 20,
            ),
            Expanded(
              child: Row(
                children: <Widget>[
                  Image.network(
                    product['image'],
                    width: 100,
                    height: 100,
                  ),
                  Container(
                    width: 20,
                  ),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Container(
                        width: 200,
                        child: Text(
                          product['title'],
                          maxLines: 2,
                          overflow: TextOverflow.ellipsis,
                          style: TextStyle(fontSize: 20),
                        ),
                      ),
                      Container(
                        height: 10,
                      ),
                      Row(
                        children: <Widget>[
                          product['categoryName'] != null
                              ? Container(
                                  child: Text("${product['categoryName']}"),
                                  width: 100,
                                )
                              : Container(),
                          product['promotionName'] != null
                              ? Text("${product['promotionName']}")
                              : Text("")
                        ],
                      ),
                    ],
                  ),
                ],
              ),
            ),
            GestureDetector(
              child: Icon(
                Icons.mode_edit,
                color: Colors.grey,
              ),
              onTap: () =>
                  jumpToEditCategory(context, product, _onBackFromEdit),
            ),
            Container(
              width: 20,
            )
          ],
        ),
        Container(
          height: 10,
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
