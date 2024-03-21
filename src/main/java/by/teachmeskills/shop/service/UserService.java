package by.teachmeskills.shop.service;

import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.api.User.UserResponse;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.mapper.UserMapper;
import by.teachmeskills.shop.repository.interfaces.UserRepository;
import by.teachmeskills.shop.entity.User;

import java.util.Collection;

public class UserService {


    public Collection<User> all(){
        UserRepository userRepository = new by.teachmeskills.shop.repository.UserRepository();
        return userRepository.all();
    }
    public UserResponse register(UserRequest userRequest){
        UserMapper userMapper = new UserMapper();
        UserRepository userRepository = new by.teachmeskills.shop.repository.UserRepository();
        User user = userMapper.toEntity(userRequest);
        userRepository.add(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse authorize(String login, String password) throws UniversalExseption {
        UserRepository userRepository = new by.teachmeskills.shop.repository.UserRepository();
        User user = userRepository.loginSearch(login);
        if (user == null) {
            throw new UniversalExseption("пользователь не существует");
        } else if (!user.getPassword().equals(password)) {
            throw new UniversalExseption("неверный пароль");
        }
        UserMapper userMapper = new UserMapper();
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;


    }



}
