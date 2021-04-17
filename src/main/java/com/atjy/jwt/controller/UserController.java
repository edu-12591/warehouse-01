package com.atjy.jwt.controller;

import com.atjy.jwt.entity.User;
import com.atjy.jwt.service.UserService;
import com.atjy.jwt.util.JWTUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xpath.internal.objects.XObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/login")
    public Map<String, Object> login(User user) {

        Map<String, Object> map = new HashMap<>();
        if (user.getName() == null || user.getPassword() == null) {
            map.put("msg", "请输入账户或密码");
            return map;
        }
        try {
            User login = userService.login(user);
            Map<String, String> payload = new HashMap<String, String>();
            System.out.println(login);
            payload.put("username", login.getName());
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("user/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        return map;
    }
}
