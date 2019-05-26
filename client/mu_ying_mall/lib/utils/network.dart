import 'package:dio/dio.dart';
import 'jump.dart';

final baseUrl = "http://192.168.1.107:8080/api/";
//final baseUrl = "http://10.0.2.2:8080/api/";

final Dio _dio = Dio();

const GET = "get";
const POST = "post";

get(String path, Function callback,
    {Map<String, String> params,
    Map<String, String> headers,
    Function errorCallback,
    context}) async {
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
      method: GET,
      headers: headers,
      errorCallback: errorCallback,
      context: context);
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
    Function errorCallback,
    context}) async {
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
        if (errorMsg.contains('token校验失败')) {
          _jumpToLogin(context);
        } else {
          _handError(errorCallback, errorMsg);
        }
      }
    }
  } catch (exception) {
    _handError(errorCallback, exception.toString());
  }
}

_jumpToLogin(context) {
  if (context != null) {
    jumpToLogin(context, (result) {});
  } else {
    print("context is null");
  }
}

_handError(Function errorCallback, String errorMsg) {
  if (errorCallback != null) {
    errorCallback(errorMsg);
  }
  print("网络请求错误 :" + errorMsg);
}
