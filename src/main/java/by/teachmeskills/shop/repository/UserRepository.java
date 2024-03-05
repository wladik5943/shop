package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.ShopEntity;
import by.teachmeskills.shop.entity.User;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;

public class UserRepository implements ShopRepository, Serializable {



    public void updateUser(User user){
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        User[] arr = new User[users.size()];
        users.toArray(arr);
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].getId() == user.getId()){
                arr[i] = user;
                break;
            }
        }
        users = Arrays.stream(arr).toList();
        SerializeUser.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }


    public User idSearch(int id){
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for(User temp: users)
            if(temp.getId() == id)
                return temp;
        return null;
    }

    public User loginSearch(String login){
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for(User temp: users)
            if(temp.getLogin().equals(login))
                return temp;
        return null;
    }

    @Override
    public void add(ShopEntity ent) {
        User user = (User)ent;
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        user.setId(users.size() + 1);
        users.add(user);
        SerializeUser.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public void deleteByld(int userId) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for(User temp: users){
            if(temp.getId() == userId) {
                users.remove(temp);
                break;
            }
        }
        SerializeUser.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }


    public Collection<User> all() {
        return SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
    }

}
