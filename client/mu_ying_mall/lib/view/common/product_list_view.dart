import 'package:flutter/material.dart';
import '../../utils/network.dart';

buildProductList(List productList, int index) {
  if (productList.length <= index) {
    return null;
  }
  if (!index.isOdd) {
    var dataLeft, dataRight;
    if (index < productList.length) {
      dataLeft = productList[index];
    }
    if ((index + 1) < productList.length) {
      dataRight = productList[index + 1];
    }

    return _buildProductTwoItem(dataLeft, dataRight);
  } else {
    return Container();
  }
}

_buildProductTwoItem(dataLeft, dataRight) {
  return Container(
    padding: EdgeInsets.all(0),
    child: Row(
      children: <Widget>[
        Expanded(
          child: _rendProductItem(dataLeft),
          flex: 1,
        ),
        (dataRight != null)
            ? Expanded(
                child: _rendProductItem(dataRight),
                flex: 1,
              )
            : Expanded(
                child: Container(),
                flex: 1,
              ),
      ],
    ),
  );
}

_rendProductItem(data) {
  print(data);
  if (data == "") {
    return Container();
  }
  return AspectRatio(
    aspectRatio: 0.8,
    child: Card(
      child: Column(
        children: <Widget>[
          Padding(
            child: LayoutBuilder(builder: (BuildContext context, BoxConstraints constraints) {
              return Image.network(data['image'],width: constraints.maxWidth,height: constraints.maxWidth,);
            }),
            padding: EdgeInsets.all(5),
          ),
          Container(
            alignment: AlignmentDirectional.centerStart,
            child: Text(
              "  ${data['title']}",
              maxLines: 1,
              style: TextStyle(fontSize: 13, fontWeight: FontWeight.bold),
              overflow: TextOverflow.ellipsis,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 3.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Row(
                  children: <Widget>[
                    Text(
                      "  ￥",
                      style: TextStyle(
                          color: Colors.red,
                          fontSize: 8,
                          fontWeight: FontWeight.bold),
                    ),
                    Text(
                      "${data['original_price']}",
                      style: TextStyle(
                          color: Colors.red,
                          fontSize: 14,
                          fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                Text(
                  "已售${data['sell_num']}件  ",
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 14,
                  ),
                )
              ],
            ),
          )
        ],
      ),
    ),
  );
}

//{ sell_num: 6736, original_price: 24.8, coupon_value: 5,title: 【4条装】男童纯棉平角舒适内裤, price_tag: 券后,}
