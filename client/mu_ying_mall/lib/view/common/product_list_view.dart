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
  if(data == ""){
    return Container();
  }
  return AspectRatio(
    aspectRatio: 0.8,
    child: Card(
      child: Column(
        children: <Widget>[
          Padding(
            child: Image.network(data['image']),
            padding: EdgeInsets.all(5),
          ),
          Text(
            data['title'],
            maxLines: 1,
            overflow: TextOverflow.ellipsis,
          ),
        ],
      ),
    ),
  );
}
