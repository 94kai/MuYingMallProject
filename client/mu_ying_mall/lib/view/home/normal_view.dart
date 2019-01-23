import 'package:flutter/material.dart';
import '../common/product_list_view.dart';
import '../../utils/network.dart';
import '../../utils/jump.dart';
class NormalView extends StatefulWidget {

  final categoryId;

  NormalView(this.categoryId);

  @override
  NormalViewState createState() {
    return new NormalViewState();
  }
}

class NormalViewState extends State<NormalView> with AutomaticKeepAliveClientMixin<NormalView>{
  List<dynamic> productList = [""];

  @override
  void initState() {
    super.initState();
    get("queryProductsByCategory", (data) {
      setState(() {
        productList = data;
      });
    }, params: {"categoryId": widget.categoryId});  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ListView.builder(
      padding: EdgeInsets.only(top: 10),
      itemBuilder: (context, index) => buildProductList(productList, index,(data)=>jumpToProductDetail(context,data)),
    );
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
}
