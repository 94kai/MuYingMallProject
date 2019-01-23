import 'package:flutter/material.dart';
import '../../utils/network.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import '../../utils/jump.dart';

class PromotionView extends StatefulWidget {
  var promotionList;

  var newsList;

  @override
  PromotionViewState createState() {
    return new PromotionViewState();
  }

  PromotionView(this.promotionList, this.newsList);
}

class PromotionViewState extends State<PromotionView> {
  @override
  Widget build(BuildContext context) {
    return _renderPromotion();
  }

  @override
  void initState() {
    super.initState();
  }

  ///渲染热门活动view
  _renderPromotion() {
    return Container(
      margin: EdgeInsets.fromLTRB(5, 10, 5, 10),
      child: Material(
        borderRadius: BorderRadius.circular(10),
        color: Colors.white,
        child: Column(
          children: <Widget>[
            _renderHeadNews(),
            Container(
              color: Colors.grey,
              height: 1,
            ),
            _renderPromotionBody()
          ],
        ),
      ),
    );
  }

  ///渲染头条新闻view
  _renderHeadNews() {
    return Container(
      height: 40,
      padding: EdgeInsets.all(10),
      child: Row(
        children: <Widget>[
          Text(
            "母婴资讯: ",
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          Expanded(
            flex: 1,
            child: Swiper(
              scrollDirection: Axis.vertical,
              itemBuilder: (BuildContext context, int index) {
                return Text(widget.newsList[index]['news'],
                    maxLines: 1, overflow: TextOverflow.ellipsis);
              },
              itemCount: widget.newsList.length,
              autoplay: widget.newsList.length > 1,
            ),
          ),
        ],
      ),
    );
  }

  ///渲染促销信息
  _renderPromotionBody() {
    final List<TableRow> children = [];
    for (int index = 0; index < widget.promotionList.length; index++) {
      if (!index.isOdd) {
        if (index + 2 <= widget.promotionList.length) {
          children.add(TableRow(
              children: _renderPromotionItem(
                  widget.promotionList.getRange(index, index + 2))));
        }
      }
    }
    return Container(
      child: Table(
        border: TableBorder(
            horizontalInside: BorderSide(color: Colors.grey, width: 1),
            verticalInside: BorderSide(color: Colors.grey, width: 1)),
        columnWidths: {
          0: FlexColumnWidth(1),
          1: FlexColumnWidth(1),
        },
        children: children,
      ),
    );
  }

  ///渲染促销信息item
  _renderPromotionItem(promotions) {
    return promotions.map<Widget>((promotion) {
      var title = promotion['title'];
      List products = promotion['productsList'];
      if (products == null || products.length < 1) {
        return Container();
      }
      return Container(
        margin: EdgeInsets.all(5),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              title,
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            Row(
              children: <Widget>[
                Expanded(
                  child: GestureDetector(
                      onTap: () => jumpToProductDetail(context, products[0]),
                      child: Padding(
                          padding: EdgeInsets.all(5),
                          child: AspectRatio(
                            aspectRatio: 1,
                            child: Image.network(products[0]['image']),
                          ))),
                  flex: 1,
                ),
                Expanded(
                  child: GestureDetector(
                      onTap: () => jumpToProductDetail(context, products[1]),
                      child: Padding(
                        padding: EdgeInsets.all(5),
                        child: AspectRatio(
                          aspectRatio: 1,
                          child: Image.network(products[1]['image']),
                        ),
                      )),
                  flex: 1,
                ),
              ],
            )
          ],
        ),
      );
    }).toList();
  }
}
