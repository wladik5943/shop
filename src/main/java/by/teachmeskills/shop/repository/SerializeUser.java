package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.User;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

public class SerializeUser implements Serializable {

    private Collection<User> users = new LinkedList<>();

    public static void serialize(Collection<User> users, String path){
        try(ObjectOutputStream objOStr = new ObjectOutputStream(new FileOutputStream(path, false))){
            SerializeUser serializeUser = new SerializeUser(users);
            objOStr.writeObject(serializeUser);
            objOStr.flush();
        }
        catch (IOException e){
            new RuntimeException(e);
        }
    }

    public static Collection<User> deSerialize(String path) {
        SerializeUser serializeUser = new SerializeUser();
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            serializeUser = (SerializeUser) objectInputStream.readObject();

        }
        catch (IOException e){
            new RuntimeException(e);
        }
        catch (ClassNotFoundException e){
            new RuntimeException(e);
        }
        return serializeUser.getUsers();
    }

    public SerializeUser() {
    }

    public SerializeUser(Collection<User> users) {
        this.users = users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

}
