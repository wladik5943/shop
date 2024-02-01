package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.User;

import java.io.*;
import java.util.Collection;

public class FileRepository implements ShopRepository, Serializable {

    public void serialize(Collection<User> users, String path){
        try(ObjectOutputStream objOStr = new ObjectOutputStream(new FileOutputStream(path))){
            objOStr.write(users.);
        }
        catch (IOException e){

        }
    }
    @Override
    public void add(User user) {

    }

    @Override
    public void deleteByld(int userId) {

    }

    @Override
    public Collection<User> allUsers() {
        return null;
    }
}
