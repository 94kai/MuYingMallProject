import 'package:flutter/material.dart';

class TopView extends StatefulWidget {
  var _opacity = 0.0;

  TopView(this._opacity);

  @override
  TopViewState createState() {
    return new TopViewState();
  }
}

class TopViewState extends State<TopView> {
  @override
  Widget build(BuildContext context) {
    return Container(
        height: kTextTabBarHeight + MediaQuery.of(context).padding.top,
        child: Column(
          children: <Widget>[
            Container(
              color: Colors.white,
              height: MediaQuery.of(context).padding.top,
            ),
            Stack(
              children: <Widget>[
                IconButton(
                  onPressed: () {},
                  icon: Icon(
                    Icons.arrow_back_ios,
                    color: Colors.black,
                  ),
                ),
                Opacity(
                  opacity: widget._opacity,
                  child: Material(
                      elevation: 5,
                      child: Stack(
                        children: <Widget>[
                          IconButton(
                            onPressed: () => Navigator.of(context).pop(),
                            icon: Icon(
                              Icons.arrow_back_ios,
                              color: Colors.black,
                            ),
                          ),
                          Container(
                            alignment: AlignmentDirectional.center,
                            width: MediaQuery.of(context).size.width,
                            height: kTextTabBarHeight,
                            child: Text(
                              "商品详情",
                              style: TextStyle(
                                  fontWeight: FontWeight.bold, fontSize: 16),
                            ),
                          ),
                        ],
                      )),
                ),
              ],
            ),
          ],
        ));
  }
}
