package com.lixiaofei.community.mapper;

import com.lixiaofei.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO `community`.`question`(`user_id`, `title`, `description`, `view_count`, `comment_count`, `like_count`, `gmt_create`, `gmt_modified`)" +
            " VALUES (#{userId},#{title},#{description},#{viewCount},#{commentCount},#{likeCount},#{gmtCreate},#{gmtModified});")
    void insertQuestion(Question question);

    @Select("SELECT * FROM question ORDER BY `gmt_create` DESC LIMIT #{offset},#{size};")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question;")
    Integer questionCount();

    @Select("SELECT * FROM question WHERE id = #{id};")
    Question findById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM question WHERE user_id=#{userId} ORDER BY `gmt_create` DESC LIMIT #{offset},#{size};")
    List<Question> userList(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(*) FROM question WHERE user_id=#{userId};")
    Integer userQuestionCount(@Param(value = "userId") Integer userId);

    @Select("SELECT * FROM question WHERE id=#{parentId};")
    Question getQByParentId(@Param(value = "parentId") Integer parentId);

    @Select("SELECT `id`,`title` FROM question ORDER BY view_count DESC LIMIT 7;")
    List<Question> showHotQuestion();

    @Select("SELECT `id`,`title` FROM `community`.`question` WHERE `title`LIKE CONCAT('%',#{search},'%');")
    List<Question> findByTitle(@Param(value = "search") String search);

}