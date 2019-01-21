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
    padding: EdgeInsets.all(10),
    child: Row(
      children: <Widget>[
        Card(
          child: Text(dataLeft),
        ),
        (dataRight != null) ? Card(child: Text(dataRight)) : Container(),
      ],
    ),
  );
}
