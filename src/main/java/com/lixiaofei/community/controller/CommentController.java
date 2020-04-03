package com.lixiaofei.community.controller;

import com.lixiaofei.community.dto.CommentDTO;
import com.lixiaofei.community.dto.ResultDTO;
import com.lixiaofei.community.exception.CustomizeErrorCode;
import com.lixiaofei.community.model.Comment;
import com.lixiaofei.community.model.User;
import com.lixiaofei.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //传递本地数据到前端，并序列化为json
    @ResponseBody
    //从前端获得json格式数据，并反序列化到本地对象
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentDTO commentDTO,
                              HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        //直接通过session属性中的user键，获得对应的value
        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.USER_NO_LOGIN);
        }
        /**
         * 下面真憨，还在用token绕一圈拿到session中的token，再通过token拿到user对象
         *         Cookie[] cookies = request.getCookies();
         *         for(Cookie cookie:cookies) {
         *             if(cookie.getName().equals("token")) {
         *                 String token = cookie.getValue();
         *                 User user = userMapper.findByToken(token);
         *                 comment.setUserId(user.getId());
         *             }
         *         }
         */
        comment.setUserId(user.getId());
        commentService.insert(comment);
        return ResultDTO.ok(new ResultDTO());
    }

}
