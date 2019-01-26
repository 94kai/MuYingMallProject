import 'package:flutter/material.dart';

class DetailBaseInfoView extends StatelessWidget {
  final data;

  DetailBaseInfoView(this.data);

  @override
  Widget build(BuildContext context) {
    return _buildBaseInfoView(data);
  }
}

///构建基础信息的view
_buildBaseInfoView(data) {
  return Container(
    padding: EdgeInsets.fromLTRB(10, 5, 10, 5),
    color: Colors.white,
    child: Column(
      children: <Widget>[
        Container(
          padding: EdgeInsets.only(bottom: 10),
          alignment: AlignmentDirectional.centerStart,
          child: Text(
            data['title'],
            maxLines: 1,
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
            textAlign: TextAlign.start,
          ),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Row(
              children: <Widget>[
                Text(
                  "￥",
                  style: TextStyle(
                      color: Colors.red,
                      fontSize: 14,
                      fontWeight: FontWeight.bold),
                ),
                Text(
                  "${data['original_price']}",
                  style: TextStyle(
                      color: Colors.red,
                      fontSize: 18,
                      fontWeight: FontWeight.bold),
                ),
              ],
            ),
            Text(
              "已售${data['sell_num']}件",
              style: TextStyle(
                color: Colors.grey,
                fontSize: 14,
              ),
            )
          ],
        ),
        Container(
          height: 5,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: _buildGoodsTag(data['goods_tag']),
        ),
        Container(
          alignment: AlignmentDirectional.centerStart,
          padding: EdgeInsets.fromLTRB(0, 10, 0, 10),
          child: Text(data['details'] == null ? "" : data['details']),
        ),
      ],
    ),
  );
}

///构建goodstag（包邮、运费险等）
_buildGoodsTag(goodsTag) {
  if (goodsTag == null || goodsTag.length == 0) {
    return [Container()];
  }
  return goodsTag
      .map<Widget>((tag) => Row(
            children: <Widget>[
              Icon(
                Icons.bookmark_border,
                color: Color(0xFFFF8A80),
              ),
              Text(
                tag,
                style: TextStyle(
                  color: Colors.grey,
                ),
              )
            ],
          ))
      .toList();
}
