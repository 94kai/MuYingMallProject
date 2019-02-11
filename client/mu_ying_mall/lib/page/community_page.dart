import 'package:flutter/material.dart';

class CommunityPage extends StatefulWidget {
  @override
  CommunityPageState createState() {
    return new CommunityPageState();
  }
}

class CommunityPageState extends State<CommunityPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.amber,
      child: Center(
        child: Text("社区"),
      ),
    );
  }
}
