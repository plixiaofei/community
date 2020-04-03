package com.lixiaofei.community.controller;

import com.lixiaofei.community.dto.PaginationDTO;
import com.lixiaofei.community.dto.QuestionDTO;
import com.lixiaofei.community.mapper.UserMapper;
import com.lixiaofei.community.model.User;
import com.lixiaofei.community.service.CommentService;
import com.lixiaofei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "7")Integer size,
                          HttpServletRequest request,
                          Model model) {
        User user = (User)request.getSession().getAttribute("user");
        PaginationDTO paginationDTO = new PaginationDTO();
        if("my-questions".equals(action)) {
            action = "my-questions";
            model.addAttribute("action",action);
            model.addAttribute("actionName","我的问题");

            paginationDTO = questionService.userList(user.getId(),page,size);
        }
        if("my-replies".equals(action)) {
            action = "my-replies";
            model.addAttribute("action",action);
            model.addAttribute("actionName","我回复的问题");

            paginationDTO = questionService.commentList(user.getId(),page,size);
        }
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
