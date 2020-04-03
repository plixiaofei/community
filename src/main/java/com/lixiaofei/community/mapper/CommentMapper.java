package com.lixiaofei.community.mapper;

import com.lixiaofei.community.model.Comment;
import com.lixiaofei.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO `community`.`comment` (`comment`,`user_id`,`parent_id`,`type`,`gmt_create`) VALUES (#{comment},#{userId},#{parentId},#{type},#{gmtCreate});")
    void insert(Comment comment);

    @Select("SELECT * FROM comment WHERE `id`=#{parentId};")
    Comment findById(@Param(value="parentId") Integer parentId);

    @Select("SELECT * FROM comment WHERE `parent_id`=#{parentId};")
    List<Comment> findAllByParentId(@Param(value="parentId") Integer parentId);


    @Select("SELECT COUNT(DISTINCT(parent_id)) FROM comment WHERE user_id=#{userId};")
    Integer commentTotalCountByUserId(@Param(value = "userId") Integer userId);

    @Select("SELECT DISTINCT(parent_id) FROM comment WHERE user_id=#{userId} ORDER BY `parent_id` DESC LIMIT #{offset},#{size};")
    Integer[] commentList(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}
