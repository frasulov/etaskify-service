package az.abb.etaskifyservice.mapper;


import az.abb.etaskifyservice.dto.UserDto;
import az.abb.etaskifyservice.requests.CreateUserRequest;
import az.abb.etaskifyservice.requests.SignupRequest;
import az.abb.etaskifyservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    User toUserFromSignupRequest(SignupRequest request);

    @Mapping(source = "email", target = "email")
    User toUserFromCreateUserRequest(CreateUserRequest request);

    @Mapping(source = "id", target = "id")
    UserDto toUserDto(User user);

    List<UserDto> toDtoList(List<User> userList);
}
