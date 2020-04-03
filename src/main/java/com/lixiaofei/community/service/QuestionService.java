package com.lixiaofei.community.service;

import com.lixiaofei.community.dto.PaginationDTO;
import com.lixiaofei.community.dto.QuestionDTO;
import com.lixiaofei.community.exception.CustomizeErrorCode;
import com.lixiaofei.community.exception.CustomizeException;
import com.lixiaofei.community.mapper.CommentMapper;
import com.lixiaofei.community.mapper.QuestionExtMapper;
import com.lixiaofei.community.mapper.QuestionMapper;
import com.lixiaofei.community.mapper.UserMapper;
import com.lixiaofei.community.model.Question;
import com.lixiaofei.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * service层完成复杂的表查询，一般用到BeanUtils.cp方法
 * 主要思想就是创建该question并赋值给questionDTO
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    public PaginationDTO list( Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.questionCount();
        paginationDTO.setPagination(totalCount,page,size);

        if(page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        if(page < 1) {
            page = 1;
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            User user = userMapper.findById(question.getUserId());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO userList(Integer userId,Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.userQuestionCount(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        if(page < 1) {
            page = 1;
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.userList(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            User user = userMapper.findById(question.getUserId());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.findById(id);
        if(question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.findById(question.getUserId());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }


    //通过userId找到parentId
    public PaginationDTO commentList(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = commentMapper.commentTotalCountByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        if(page < 1) {
            page = 1;
        }
        Integer offset = size * (page - 1);
        Integer[] comments = commentMapper.commentList(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Integer i:comments) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(comments,questionDTO);
            Question question = questionMapper.getQByParentId(i);
            BeanUtils.copyProperties(question,questionDTO);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public void incView(Integer id) {
        questionExtMapper.increaseViewCount(id);
    }
}
