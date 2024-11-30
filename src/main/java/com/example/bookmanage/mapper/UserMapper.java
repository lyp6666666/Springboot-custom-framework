package com.example.bookmanage.mapper;

import com.example.bookmanage.pojo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user(account,password) values(#{account},#{password})")
    void insertUser(User user);

    @Select("select * from user where account = #{account}")
    User findUserByAccount(String account);

    @Update("UPDATE user SET account = #{newUser.account}, password = #{newUser.password} WHERE account = #{account}")
    void updateUserByAccount(@Param("account")String sccount, @Param("newUser")User newUser);

    User login(User user);
}
