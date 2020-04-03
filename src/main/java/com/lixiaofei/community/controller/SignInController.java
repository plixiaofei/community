package com.lixiaofei.community.controller;

import com.lixiaofei.community.model.User;
import com.lixiaofei.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class SignInController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @PostMapping("/signIn")
    public String doSignIn(
            @RequestParam("email") String email,    //RequestParam注解获得前端name属性
            @RequestParam("userName") String userName,
            @RequestParam("confirmPassword") String password,
            Model model,
            HttpServletResponse response
            ) {
        User user = new User();
        if(email.equals(userMapper.queryEmail(email))) {
            model.addAttribute("email",email);
            model.addAttribute("userName",userName);
            System.out.println("创建用户失败!");
            model.addAttribute("failedC","创建用户失败,一般是由于邮箱相同导致~");
            return "/signIn";
        } else {
            user.setEmail(email);
            user.setUserName(userName);
            user.setPassword(password);
            user.setCreateTime(System.currentTimeMillis());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userMapper.insertUser(user);
            System.out.println(userName);
            System.out.println("创建用户成功");
            response.addCookie(new Cookie("token",user.getToken()));
        }
        return "redirect:/";
    }
}
