import 'package:shared_preferences/shared_preferences.dart';
import 'network.dart';

///校验登录状态。成功不代表就有效。还需要携带token让服务端校验
Future<bool> checkLoginState() async {
  var token = await getToken();
  return token != null && token.length > 0;
}

///获取tokem
Future<String> getToken() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  return prefs.getString('token');
}

///登录成功或者退出登录时，更新token
_updateToken(token) async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  prefs.setString('token', token);
}

///登录
login(userName, passWord, successCallback, errorCallback) {
  post(
      'login',
      (data) {
        ///保存token
        _updateToken(data['token']);
        successCallback(data);
      },
      params: {
        'userName': userName,
        'passWord': passWord,
      },
      errorCallback: (error) {
        ///移除token
        _updateToken('');
        errorCallback(error);
      });
}

///注册
register(userName, passWord, successCallback, errorCallback) {
  post(
      'register',
      (data) {
        _updateToken(data['token']);
        successCallback(data);
      },
      params: {
        'userName': userName,
        'passWord': passWord,
      },
      errorCallback: (error) {
        ///移除token
        _updateToken('');
        errorCallback(error);
      });
}
