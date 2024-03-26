package by.teachmeskills.shop.mapper;

import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.api.User.UserResponse;
import by.teachmeskills.shop.entity.User;

public class UserMapper {

    public User toEntity(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return user;
    }

    public UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setLogin(user.getLogin());
        userResponse.setPassword(user.getPassword());
        userResponse.setRole(user.getRole());
        return userResponse;
    }

}
