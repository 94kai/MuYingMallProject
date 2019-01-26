import 'package:flutter/material.dart';
import '../../utils/jump.dart';

class DetailRecomendView extends StatelessWidget {
  var recommendData;

  DetailRecomendView(this.recommendData);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
              '相似推荐',
              style: TextStyle(fontSize: 18),
            ),
          ),
          Container(
            width: MediaQuery.of(context).size.width,
            height: 180,
            child: ListView.builder(
                itemBuilder: (context, index) =>
                    _buildRecommendItem(context, recommendData[index]),
                itemCount: recommendData.length,
                scrollDirection: Axis.horizontal),
          ),
        ],
      ),
    );
  }

  _buildRecommendItem(context, itemData) {
    return GestureDetector(
      child: Container(
          width: 120,
          padding: EdgeInsets.all(5),
          child: Column(
            children: <Widget>[
              Image.network(
                itemData['image'],
                width: 110,
                height: 110,
                fit: BoxFit.fill,
              ),
              Container(
                height: 5,
              ),
              Text(
                itemData['title'],
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              Text(
                "￥${itemData['original_price']}",
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
                style:
                    TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
              )
            ],
          )),
      onTap: () => jumpToProductDetail(context, itemData['id']),
    );
  }
}

//{title: 潍坊2019新款蓝蝴蝶风筝, sell_num: 161, original_price: 34.99,, image: htg_.webp, id: 18152768,}
