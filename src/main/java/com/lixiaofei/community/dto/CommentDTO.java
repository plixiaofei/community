package com.lixiaofei.community.dto;

import com.lixiaofei.community.model.Comment;
import com.lixiaofei.community.model.Question;
import com.lixiaofei.community.model.User;
import org.springframework.beans.BeanUtils;

/**
 * 用于和comment对接
 */
public class CommentDTO {
    private String comment;
    private Long gmtCreate;
    private Integer parentId;
    private Integer type;
    private User user;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
