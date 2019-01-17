package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.User;
import com.lq.muyingmall.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;
    private User save;

    @RequestMapping(name = "/login", method = {RequestMethod.POST})
    public String login() {
//        userRepository.save(new User());
//        userRepository.save(new User());
        return "HelloWrold";
    }


    @RequestMapping(name = "/register", method = {RequestMethod.GET})
    public String register() {
        User xx = userRepository.findByUserName("xx1");
        if (xx != null) {
            return "eror";
        } else {
            save = userRepository.save(new User("xx", "薛s3凯31", "1"));
            return "save";
        }
    }
}
