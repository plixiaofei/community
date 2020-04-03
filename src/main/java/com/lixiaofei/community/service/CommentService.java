package com.lixiaofei.community.service;

import com.lixiaofei.community.dto.CommentDTO;
import com.lixiaofei.community.dto.PaginationDTO;
import com.lixiaofei.community.dto.QuestionDTO;
import com.lixiaofei.community.enums.CommentTypeEnum;
import com.lixiaofei.community.exception.CustomizeErrorCode;
import com.lixiaofei.community.exception.CustomizeException;
import com.lixiaofei.community.mapper.CommentMapper;
import com.lixiaofei.community.mapper.QuestionExtMapper;
import com.lixiaofei.community.mapper.QuestionMapper;
import com.lixiaofei.community.mapper.UserMapper;
import com.lixiaofei.community.model.Comment;
import com.lixiaofei.community.model.Question;
import com.lixiaofei.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * combine comment and question
 */

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        //JSON问题
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_WRONG);
        }

        //回复问题或者回复评论
        if(comment.getType() == CommentTypeEnum.QUESTION.getType()) {
            //回复问题
            Question question = questionMapper.findById(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                //判断问题的
            } else {
                commentMapper.insert(comment);
                questionExtMapper.increaseCommentCount(comment.getParentId());
            }
        } else {
            //回复评论
            Comment dbComment = commentMapper.findById(comment.getParentId());
            if(dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            } {
                commentMapper.insert(comment);
            }
        }
    }

    public List<CommentDTO> findByParentId(Integer parentId) {
        List<Comment> comments = commentMapper.findAllByParentId(parentId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment:comments) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            User user = userMapper.findById(comment.getUserId());
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
