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

///获取username
Future<String> getUserName() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  return prefs.getString('userName');
}

///获取username
getUserNameAndToken(callback) async {
  getToken().then(
      (token) => getUserName().then((userName) => callback(userName, token)));
}

///登录成功或者退出登录时，更新token
_updateToken(token) async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  prefs.setString('token', token);
}

///登录成功或者退出登录时，更新用户名
_updateUserName(userName) async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  prefs.setString('userName', userName);
}

///登录
login(userName, passWord, successCallback, errorCallback) {
  post(
      'login',
      (data) {
        ///保存token
        _updateToken(data['token']);
        _updateUserName(userName);
        successCallback(data);
      },
      params: {
        'userName': userName,
        'passWord': passWord,
      },
      errorCallback: (error) {
        ///移除token
        _updateToken('');
        _updateUserName('');
        errorCallback(error);
      });
}

///退出登录
logout() {
  _updateToken('');
  _updateUserName('');
}

///注册
register(userName, passWord, successCallback, errorCallback) {
  post(
      'register',
      (data) {
        _updateToken(data['token']);
        _updateUserName(userName);
        successCallback(data);
      },
      params: {
        'userName': userName,
        'passWord': passWord,
      },
      errorCallback: (error) {
        ///移除token
        _updateToken('');
        _updateUserName('');
        errorCallback(error);
      });
}
