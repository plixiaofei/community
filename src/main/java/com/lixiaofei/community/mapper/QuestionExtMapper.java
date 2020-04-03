package com.lixiaofei.community.mapper;

import com.lixiaofei.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QuestionExtMapper {

    @Update("UPDATE `community`.`question` SET `view_count`=(view_count + 1) WHERE id=#{id};")
    void increaseViewCount(@Param(value = "id") Integer id);

    @Update("UPDATE `community`.`question` SET `like_count`=(like_count+1) WHERE id=#{id};")
    void increaseLikeCount(@Param(value = "id") Integer id);

    @Update("UPDATE `community`.`question` SET `comment_count`=(comment_count+1) WHERE id=#{id};")
    void increaseCommentCount(@Param(value = "id") Integer id);
}
