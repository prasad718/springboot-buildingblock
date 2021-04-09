package com.stacksimplifly.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.stacksimplifly.entity.User;
import com.stacksimplifly.entity.UserMsDto;

@Mapper(componentModel ="Spring")
public interface UserMapper {

	UserMapper INSTANCE=Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDto
	
	UserMsDto  userToUserDto(User user);
	
	//UserList to UserMsDto List
	
	List<UserMsDto> userToUserMsDtos(List<User> users);
	
}
