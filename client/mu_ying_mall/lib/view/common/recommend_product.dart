import 'package:flutter/material.dart';
import '../../utils/network.dart';
class RecommendProductList extends StatefulWidget{
  @override
  RecommendProductListState createState() {
    return new RecommendProductListState();
  }
}

class RecommendProductListState extends State<RecommendProductList> {
  @override
  initState() {
    super.initState();

  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Text("recommend");
  }
}


buildRecommendProductList(recommendList){
  if (recommendList.length==0) {
    return null;
  }else{
    recommendList.map().toList();
  }

}