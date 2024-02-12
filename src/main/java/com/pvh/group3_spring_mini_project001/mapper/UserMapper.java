package com.pvh.group3_spring_mini_project001.mapper;

import com.pvh.group3_spring_mini_project001.model.User;
import com.pvh.group3_spring_mini_project001.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    @Mapping(source = "id",target = "userId")
    UserDto toUserDTO(User user);
}
