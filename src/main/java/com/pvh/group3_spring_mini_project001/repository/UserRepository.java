package com.pvh.group3_spring_mini_project001.repository;

import com.pvh.group3_spring_mini_project001.model.User;
import com.pvh.group3_spring_mini_project001.model.request.UserRequest;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository {
    @Select("""
            insert into user_tb(user_email,user_password) values(#{user.email},#{user.password}) 
            returning *
            """)
    @Results(id="userMap",value = {
            @Result(property = "id",column = "user_id"),
            @Result(property = "email",column = "user_email"),
            @Result(property = "password",column = "user_password")
    })
    User register(@Param("user")UserRequest request);
    @Select("""
            select * from user_tb where user_email=#{email}
            """)
     @ResultMap("userMap")
    User getUserByEmail(String email);
}
