package com.atjy.jwt.mapper;

import com.atjy.jwt.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User login(User user);
}
