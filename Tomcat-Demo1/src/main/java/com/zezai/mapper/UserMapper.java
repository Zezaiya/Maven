package com.zezai.mapper;

import com.zezai.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User select(@Param("username") String username, @Param("password") String password);

    @Select("select * from tb_user where username=#{username}")
    User check(@Param("username") String username);

    @Insert("insert into tb_user values (null,#{username},#{password})")
     void register(@Param("username") String username, @Param("password") String password);
}
