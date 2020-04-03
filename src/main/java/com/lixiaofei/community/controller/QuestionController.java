package com.lixiaofei.community.controller;

import com.lixiaofei.community.dto.CommentDTO;
import com.lixiaofei.community.dto.QuestionDTO;
import com.lixiaofei.community.model.Comment;
import com.lixiaofei.community.model.User;
import com.lixiaofei.community.service.CommentService;
import com.lixiaofei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.findById(id);
        model.addAttribute("questionDTO",questionDTO);
        questionService.incView(id);
        List<CommentDTO> comments = commentService.findByParentId(id);
        model.addAttribute("comments",comments);
        return "question";
    }

}
