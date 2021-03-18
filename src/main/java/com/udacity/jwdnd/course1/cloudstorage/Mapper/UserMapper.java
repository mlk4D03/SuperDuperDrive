package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    public User getUser(String username);

    @Select("SELECT userid FROM USERS WHERE username = #{username}")
    public Integer getUserId(String username);

    @Insert("INSERT INTO USERS (username,salt,password,firstname,lastname) VALUES(#{username},#{salt},#{password},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    public int insertUser(User user);
}
