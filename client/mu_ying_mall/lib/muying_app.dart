import 'package:flutter/material.dart';

import 'page/main_page.dart';
class MuYingApp extends StatefulWidget{
  @override
  MuYingAppState createState() {
    return new MuYingAppState();
  }
}

class MuYingAppState extends State<MuYingApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "母婴商城",
      theme: new ThemeData(
        primarySwatch: Colors.pink,),
      home: MainPage(),
    );
  }
}