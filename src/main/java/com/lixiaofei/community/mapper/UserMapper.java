package com.lixiaofei.community.mapper;

import com.lixiaofei.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO `users` (`email`,`username`,`password`,`create_time`,`token`) VALUES (LOWER(#{email}),#{userName},#{password},#{createTime},#{token})")
    void insertUser(User user);

    @Select("SELECT * FROM `users` WHERE `username`=#{userName};")
    String queryUser(User user);

    @Select("SELECT * FROM `users` WHERE `email`=LOWER(#{email}) AND `password`=#{password};")
    User verifiedAccount(String email, String password);

    @Select("SELECT * FROM `users` WHERE token=#{token};")
    User findByToken(String token);

    @Select("SELECT `email` FROM `users` WHERE `email`=LOWER(#{email}) LIMIT 1;")
    String queryEmail(String email);

    @Select("SELECT * FROM `users` WHERE `id`=#{id}")
    User findById(@Param("id") Integer id);

}
