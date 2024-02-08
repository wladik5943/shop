package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.User;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class FileRepository implements ShopRepository, Serializable {
    private Collection<User> users = new LinkedList<>();
    private int count = 0;
    public void serialize(Collection<User> users, String path){
        try(ObjectOutputStream objOStr = new ObjectOutputStream(new FileOutputStream(path))){
            objOStr.writeObject((Object)users);
        }
        catch (IOException e){
            new RuntimeException(e);
        }
    }

    public Collection<User> deSerialize(String path) {

        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Collection<User> users = new LinkedList<User>();

        }
        catch (IOException e){
            new RuntimeException(e);
        }
        catch (ClassNotFoundException e){
            new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void add(User user) {
        users.add(user);
        count++;
        serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
    }

    @Override
    public void deleteByld(int userId) {

    }

    @Override
    public Collection<User> allUsers() {
        return deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
    }


}
