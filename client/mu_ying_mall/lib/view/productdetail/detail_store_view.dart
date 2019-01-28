import 'package:flutter/material.dart';

class DetailStoreView extends StatelessWidget {
  final data;

  DetailStoreView(this.data);

  @override
  Widget build(BuildContext context) {
    return _buildStoreInfoView(data);
  }
}

///构建店铺信息的view
_buildStoreInfoView(data) {
  if(data['store']==null){
    return Container();
  }
  return Container(
    padding: EdgeInsets.fromLTRB(10, 10, 10, 5),
    color: Colors.white,
    child: Column(children: <Widget>[
      Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Image.network(
            data['store']['shop_logo'],
            width: 70,
            height: 70,
            fit: BoxFit.fill,
          ),
          Padding(
            padding: const EdgeInsets.only(top: 8.0),
            child: Text(
              '  ${data['store']['shop_name']}',
              maxLines: 1,
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
              textAlign: TextAlign.start,
            ),
          ),
        ],
      ),
      Container(
        height: 10,
      ),
      Container(
        color: Colors.grey,
        height: 0.4,
      ),
      Padding(
        padding: const EdgeInsets.fromLTRB(0, 10, 0, 10),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            Text(
              "宝贝描述:${data['store']['dsr']}",
              style: TextStyle(fontSize: 15),
            ),
            Text(
              "卖家服务:${data['store']['service']}",
              style: TextStyle(fontSize: 15),
            ),
            Text(
              "物流服务:${data['store']['delivery']}",
              style: TextStyle(fontSize: 15),
            ),
          ],
        ),
      ),
    ]),
  );
}
