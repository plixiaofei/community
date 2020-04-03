package com.lixiaofei.community.controller;

import com.lixiaofei.community.dto.PaginationDTO;
import com.lixiaofei.community.exception.CustomizeErrorCode;
import com.lixiaofei.community.mapper.QuestionMapper;
import com.lixiaofei.community.mapper.UserMapper;
import com.lixiaofei.community.model.Question;
import com.lixiaofei.community.service.QuestionService;
import com.sun.jdi.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "7")Integer size,
                        @RequestParam(value = "search",required = false) String search) {
        PaginationDTO pagination = questionService.list(page,size);
        List<Question> questions = questionMapper.showHotQuestion();
        List<Question> searchList = questionMapper.findByTitle(search);
        if(searchList.size() == 0 && search != null) {
            model.addAttribute("queryNotFound",CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
        } else {
            model.addAttribute("searchList",searchList);
        }
        model.addAttribute("questions",questions);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
