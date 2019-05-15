import 'package:flutter/material.dart';
import 'package:mu_ying_mall/utils/network.dart';
import 'package:mu_ying_mall/utils/login_util.dart';
import 'package:mu_ying_mall/utils/jump.dart';
import 'package:toast/toast.dart';

class AddressPage extends StatefulWidget {
  ///总金额
  double totalMoney;

  AddressPage(this.totalMoney);

  @override
  Widget build(BuildContext context) {
    return _buildScaffoldView(context);
  }

  ///构建基础视图
  Widget _buildScaffoldView(context) {}

  @override
  AddressPageState createState() {
    return AddressPageState();
  }
}

class AddressPageState extends State<AddressPage> {
  List<dynamic> _addressList = [];
  String mUserName;

  @override
  void initState() {
    super.initState();
    getUserName().then((userName) {
      mUserName = userName;
      get("queryAllAddressByUserName", (addressList) {
        setState(() {
          _addressList = addressList;
        });
      }, params: {"userName": userName});
    });
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('选择收货地址'),
          actions: <Widget>[
            GestureDetector(
              onTap: () {
                bool hasCheck = false;
                for (int i = 0; i < _addressList.length; i++) {
                  var address = _addressList[i];
                  bool isCheck =
                      address["checked"] == null ? false : address["checked"];
                  if (isCheck) {
                    hasCheck = true;
                    break;
                  }
                }
                if (hasCheck) {
                  jumpToSettleAccount(context, widget.totalMoney);
                } else {
                  Toast.show("请选择收货人", context);
                }
              },
              child: Container(
                padding: EdgeInsets.only(right: 20),
                alignment: AlignmentDirectional.center,
                child: Text('去结算'),
              ),
            ),
          ],
        ),
        body: Container(
          width: MediaQuery.of(context).size.width,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Expanded(
                child: ListView.builder(
                    itemCount: _addressList.length,
                    itemBuilder: (context, index) => _buildItem(index)),
              ),
              Container(
                  height: 50,
                  width: MediaQuery.of(context).size.width,
                  color: Colors.red,
                  alignment: AlignmentDirectional.center,
                  child: GestureDetector(
                    child: Text(
                      "+新建地址",
                      style: TextStyle(color: Colors.white, fontSize: 20),
                    ),
                    onTap: () => jumpToEditAddress(
                        context, null, mUserName, _onBackFromEdit),
                  )),
            ],
          ),
        ));
  }

  //从编辑页回来
  _onBackFromEdit(needRefresh) {
    getUserName().then((userName) {
      mUserName = userName;
      get("queryAllAddressByUserName", (addressList) {
        setState(() {
          _addressList = addressList;
        });
      }, params: {"userName": userName});
    });
  }

  //buildItem
  _buildItem(int index) {
    var address = _addressList[index];
    return Column(
      children: <Widget>[
        Row(
          children: <Widget>[
            Checkbox(
                value: address['checked'] == null ? false : address['checked'],
                onChanged: (checked) => setState(() {
                      _addressList
                          .forEach((address) => address['checked'] = false);
                      address['checked'] = checked;
                    })),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Container(
                    height: 10,
                  ),
                  Text(
                    address['address'],
                    maxLines: 1,
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  Row(
                    children: <Widget>[
                      Container(
                        width: 70,
                        child: Text(
                          address['consignee'],
                          maxLines: 1,
                        ),
                      ),
                      Container(
                        width: 100,
                        child: Text(
                          address['phoneNumber'],
                          maxLines: 1,
                        ),
                      ),
                    ],
                  ),
                  Container(
                    height: 10,
                  ),
                ],
              ),
            ),
            GestureDetector(
              child: Icon(
                Icons.mode_edit,
                color: Colors.grey,
              ),
              onTap: () => jumpToEditAddress(
                  context, address, mUserName, _onBackFromEdit),
            ),
            Container(
              width: 20,
            )
          ],
        ),
        Container(
          height: 0.1,
          width: MediaQuery.of(context).size.width,
          color: Colors.grey,
        )
      ],
    );
  }
}
