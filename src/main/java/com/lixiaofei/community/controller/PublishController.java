package com.lixiaofei.community.controller;

import com.lixiaofei.community.mapper.QuestionMapper;
import com.lixiaofei.community.model.Question;
import com.lixiaofei.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    public QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
    @PostMapping("/doPublish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            HttpServletRequest request) {
        Question question = new Question();
        User user = (User)request.getSession().getAttribute("user");
        question.setUserId(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setViewCount(0);
        question.setCommentCount(0);
        question.setLikeCount(0);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(0L);
        questionMapper.insertQuestion(question);
        return "redirect:/";
    }
}
