package by.teachmeskills.shop.service.impl;

import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.api.User.UserResponse;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.mapper.UserMapper;
import by.teachmeskills.shop.repository.UserRepository;
import by.teachmeskills.shop.entity.User;

public class UserService {

    public UserResponse register(UserRequest userRequest){
        UserMapper userMapper = new UserMapper();
        UserRepository userRepository = new UserRepository();
        User user = userMapper.toEntity(userRequest);
        userRepository.add(user);
        return userMapper.toUserResponse(user);
    }

    public void authorize(String login, String password) throws UniversalExseption {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.loginSearch(login);
        if (user == null) {
            throw new UniversalExseption("пользователь не существует");
        } else if (!user.getPassword().equals(login)) {
            throw new UniversalExseption("неверный пароль");
        }
    }



}
