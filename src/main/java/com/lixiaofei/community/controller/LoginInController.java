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

@Controller
public class LoginInController {

    @Autowired()
    private UserMapper userMapper;

    @GetMapping("/loginIn")
    public String loginIn() {
        return "loginIn";
    }

    @PostMapping("/loginIn")
    public String doLoginIn(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletResponse response,
            Model model
    ) {
        User user = userMapper.verifiedAccount(email, password);
        if(user != null) {
            System.out.println("登陆成功");
            System.out.println(user.getEmail() + user.getPassword());
            response.addCookie(new Cookie("token", user.getToken()));
        } else {
            System.out.println("登陆失败");
            model.addAttribute("failed","密码错误，登陆失败~");
            return "/loginIn";
        }
        return "redirect:/";
    }

    @GetMapping("/logOut")
    public String doLogOut(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        System.out.println("成功退出");
        return "redirect:/";
    }

}
