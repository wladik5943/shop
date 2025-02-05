package by.teachmeskills.shop.repository.userRepository.impl;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.config.serialize.SerializeUser;
import by.teachmeskills.shop.repository.userRepository.UserRepository;

import java.util.Arrays;
import java.util.Collection;

public class UserRepositorySerialize implements UserRepository {
    @Override
    public void add(User user) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        user.setId(users.size() + 1);
        users.add(user);
        SerializeUser.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public void deleteById(int userId) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for (User temp : users) {
            if (temp.getId() == userId) {
                users.remove(temp);
                break;
            }
        }
        SerializeUser.serialize(users, "D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public User loginSearch(String login) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for (User temp : users)
            if (temp.getLogin().equals(login))
                return temp;
        return null;
    }

    @Override
    public Collection<User> all() {
           return SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public void updateUser(User user) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        User[] arr = new User[users.size()];
        users.toArray(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getId() == user.getId()) {
                arr[i] = user;
                break;
            }
        }
        users.clear();
        Arrays.stream(arr).forEach(x -> {
            users.add(x);
        });
        SerializeUser.serialize(users, "D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public User idSearch(int id) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for (User temp : users)
            if (temp.getId() == id)
                return temp;
        return null;
    }
}
