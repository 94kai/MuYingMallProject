import 'package:flutter/material.dart';

class SettleAccountPage extends StatelessWidget {
  ///总金额
  double totalMoney;

  SettleAccountPage(this.totalMoney);

  @override
  Widget build(BuildContext context) {
    return _buildScaffoldView(context);
  }

  ///构建基础视图
  Widget _buildScaffoldView(context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('结算'),
        ),
        body: Container(
          width: MediaQuery.of(context).size.width,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Image.asset('images/erweima.png'),
              Container(height: 10,),
              Text(
                '您需支付$totalMoney元',
                style: TextStyle(fontSize: 18),
              ),
            ],
          ),
        ));
  }
}
