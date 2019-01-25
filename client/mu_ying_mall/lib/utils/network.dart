import 'package:dio/dio.dart';

final baseUrl = "http://10.0.2.2:8080/api/";

final Dio _dio = Dio();

const GET = "get";
const POST = "post";


getDetail(){
  return {
    "status": 0,
    "data": {
      "title": "\u6f4d\u574a\u98ce\u7b5d\u8001\u9e70\u91d1\u9c7c\u8774\u8776\u6c99\u71d5\u8759\u8760\u5361\u901a\u98ce\u7b5d",
      "id": 18174928,
      "details": "\u6b63\u5b97\u6f4d\u574a\u5236\u98ce\u7b5d\uff0c\u9aa8\u67b6\u5f88\u7262\u56fa\uff0c\u5fae\u98ce\u5c31\u80fd\u653e\u8d77\u6765\uff0c\u51fa\u95e8\u8e0f\u9752\u6625\u6e38\u5fc5\u4e0d\u53ef\u5c11\u54e6\uff0c\u5168\u5bb6\u90fd\u80fd\u53c2\u4e0e\uff0c\u4eb2\u5b50\u6d3b\u52a8\u9996\u9009\uff01",
      "pic_list": ["https:\/\/img.alicdn.com\/imgextra\/i1\/2270433976\/TB2Ibx1mwxlpuFjSszgXXcJdpXa_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i1\/2270433976\/O1CN01g1GgbE1fF3FafsbAm_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i1\/2270433976\/O1CN012kVnmu1fF3FajVDUa_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i4\/2270433976\/O1CN01jiJ1Wx1fF3FbLtYIh_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i2\/2270433976\/O1CN01bIZgsd1fF3FbsFTPp_!!2270433976.jpg_600x600.jpg_.webp", "https:\/\/img.alicdn.com\/imgextra\/i2\/2270433976\/O1CN01WL9a9W1fF3FaJfGIP_!!2270433976.jpg_600x600.jpg_.webp"],
      "price": 9.9,
      "original_price": 19.9,
      "is_tmall": 1,
      "quan_price": 10,
      "quan_start_time": "2019.01.21",
      "quan_time": "2019.01.23",
      "sell_num": 4857,
      "goods_id": "544040534929",
      "goods_tag": ["\u5305\u90ae", "\u8fd0\u8d39\u9669"],
      "goods_price_type": "\u5929\u732b\u4ef7",
      "goods_price_text": "\u5238\u540e\u4ef7",
      "goods_button_text": "\u9886\u5238\u8d2d\u4e70",
      "goods_quan_text": "\u7acb\u5373\u9886\u5238",
      "store": {
        "seller_id": "2270433976",
        "dsr": 4.8,
        "dsr_level": 0,
        "service": 4.8,
        "service_level": 1,
        "delivery": 4.8,
        "delivery_level": 1,
        "shop_name": "\u6c38\u5065\u6237\u5916\u65d7\u8230\u5e97",
        "shop_logo": "https:\/\/img.alicdn.com\/imgextra\/\/fa\/2e\/TB1fLetLpXXXXcWaXXXSutbFXXX.jpg",
        "show_button": 1,
        "is_tmall": 1
      },
      "collected": 0
    },
  };

}
get(String path, Function callback,
    {Map<String, String> params,
    Map<String, String> headers,
    Function errorCallback}) async {
  if (params != null && params.isNotEmpty) {
    StringBuffer sb = new StringBuffer("?");
    params.forEach((key, value) {
      sb.write("$key" + "=" + "$value" + "&");
    });
    String paramStr = sb.toString();
    paramStr = paramStr.substring(0, paramStr.length - 1);
    path += paramStr;
  }
  await _request("$baseUrl$path", callback,
      method: GET, headers: headers, errorCallback: errorCallback);
}

post(String path, Function callback,
    {Map<String, String> params,
    Map<String, String> headers,
    Function errorCallback}) async {
  await _request("$baseUrl$path", callback,
      method: POST,
      headers: headers,
      params: params,
      errorCallback: errorCallback);
}

_request(String url, Function callback,
    {String method,
    Map<String, String> headers,
    Map<String, String> params,
    Function errorCallback}) async {
  print("请求url:$url");

  String errorMsg;
  int errorCode;
  var data;
  try {
    Map<String, String> headerMap = headers == null ? new Map() : headers;
    Map<String, String> paramMap = params == null ? new Map() : params;

    Response res;
    if (POST == method) {
      res = await _dio.post(url,
          options: Options(headers: headerMap), data: paramMap);
    } else {
      res = await _dio.get(url, options: Options(headers: headerMap));
    }
    if (res.statusCode != 200) {
      errorMsg = "网络请求错误,状态码:" + res.statusCode.toString();
      _handError(errorCallback, errorMsg);
      return;
    }

    //以下部分可以根据自己业务需求封装,这里是errorCode>=0则为请求成功,data里的是数据部分
    //记得Map中的泛型为dynamic
    Map<String, dynamic> map = res.data;
    errorCode = map['code'];
    errorMsg = map['msg'];
    data = map['data'];

    // callback返回data,数据类型为dynamic
    //errorCallback中为了方便我直接返回了String类型的errorMsg
    if (callback != null) {
      if (errorCode >= 0) {
        callback(data);
      } else {
        _handError(errorCallback, errorMsg);
      }
    }
  } catch (exception) {
    _handError(errorCallback, exception.toString());
  }
}

_handError(Function errorCallback, String errorMsg) {
  if (errorCallback != null) {
    errorCallback(errorMsg);
  }
  print("网络请求错误 :" + errorMsg);
}
