import 'package:flutter/material.dart';
import '../../utils/network.dart';

buildRecommendProductList(List recommendProductList, int index) {
  if (recommendProductList.length <= index) {
    return null;
  }
  if (!index.isOdd) {
    var dataLeft, dataRight;
    if (index < recommendProductList.length) {
      dataLeft = recommendProductList[index];
    }
    if ((index + 1) < recommendProductList.length) {
      dataRight = recommendProductList[index + 1];
    }

    return _buildRecommendTwoItem(dataLeft, dataRight);
  } else {
    return Container();
  }
}

_buildRecommendTwoItem(dataLeft, dataRight) {
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
