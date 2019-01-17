package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.User;
import com.lq.muyingmall.domain.UserRepository;
import com.mysql.cj.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //存放token的map
    private Map<String, String> tokenMap = new HashMap<>();
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public BaseResponse login(@RequestBody User user) {
        User tempUser = userRepository.findByUserNameAndPassWord(user.getUserName(), user.getPassWord());
        if (tempUser != null) {
            String token = getTokenByUserName(user.getUserName());
            tokenMap.put(user.getUserName(), token);
            return new BaseResponse(0, "登陆成功", "124");
        }
        return new BaseResponse(-1, "登录失败", null);
    }

    @RequestMapping(value = "/hhhhh", method = {RequestMethod.GET})
    public String hhhhh(String context, @RequestHeader(value = "token", required = false) String token) {
        logger.info("hhhhh=======" + context);
        return "hhhhh";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public BaseResponse register(@RequestBody User user) {
        User tempUser = userRepository.findByUserName(user.getUserName());
        if (tempUser != null) {
            return new BaseResponse(-1, "用户名被占用", null);
        }
        User save = userRepository.save(user);
        String token = getTokenByUserName(save.getUserName());
        tokenMap.put(save.getUserName(), token);
        return new BaseResponse(0, "注册成功", token);
    }


    /**
     * 通过username获取token
     */
    private String getTokenByUserName(String userName) {
        return DigestUtils.md5DigestAsHex((userName + System.currentTimeMillis()).getBytes());
    }
}
