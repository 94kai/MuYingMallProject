package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.LoginState;
import com.lq.muyingmall.domain.User;
import com.lq.muyingmall.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //存放token的map
    public static Map<String, String> tokenMap = new HashMap<>();
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public BaseResponse<LoginState> login(@RequestBody User user) {

        User tempUser = userRepository.findByUserName(user.getUserName());
        if (tempUser == null) {
            return new BaseResponse<>(-1, "未注册");
        }
        tempUser = userRepository.findByUserNameAndPassWord(user.getUserName(), user.getPassWord());
        if (tempUser == null) {
            return new BaseResponse<>(-1, "用户名或密码错误");
        }
        String token = getTokenByUserName(user.getUserName());
        tokenMap.put(user.getUserName(), token);
        return new BaseResponse<>(0, new LoginState(token));
    }


    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public BaseResponse register(@RequestBody User user) {
        User tempUser = userRepository.findByUserName(user.getUserName());
        if (tempUser != null) {
            return new BaseResponse(-1, "用户名被占用");
        }
        String userName = user.getUserName();
        String passWord = user.getPassWord();
        if (userName == null || userName.length() < 4 || userName.length() > 11) {
            return new BaseResponse(-1, "用户名必须是4到11位");
        }

        if (passWord == null || passWord.length() < 4 || passWord.length() > 20) {
            return new BaseResponse(-1, "密码必须是4到20位");
        }
        User save = userRepository.save(user);
        String token = getTokenByUserName(save.getUserName());
        tokenMap.put(save.getUserName(), token);
        return new BaseResponse<>(0, new LoginState(token));
    }


    /**
     * 通过username获取token
     */
    private String getTokenByUserName(String userName) {
        return DigestUtils.md5DigestAsHex((userName + System.currentTimeMillis()).getBytes());
    }
}
