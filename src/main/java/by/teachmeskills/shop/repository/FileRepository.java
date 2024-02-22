package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.User;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class FileRepository implements ShopRepository, Serializable {



    public User loginSearch(String login){
        Collection<User> users = Serialize.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for(User temp: users)
            if(temp.getLogin().equals(login))
                return temp;
        return null;
    }

    @Override
    public void add(User user) {
        Collection<User> users = Serialize.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        user.setId(users.size() + 1);
        users.add(user);
        Serialize.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public void deleteByld(int userId) {
        Collection<User> users = Serialize.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for(User temp: users){
            if(temp.getId() == userId) {
                users.remove(temp);
                break;
            }
        }
        Serialize.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public Collection<User> allUsers() {
        return Serialize.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
    }

}
