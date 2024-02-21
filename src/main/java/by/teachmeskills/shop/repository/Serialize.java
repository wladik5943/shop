package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.User;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

public class Serialize implements Serializable {

    private Collection<User> users = new LinkedList<>();

    public static void serialize(Collection<User> users, String path){
        try(ObjectOutputStream objOStr = new ObjectOutputStream(new FileOutputStream(path, false))){
            Serialize serialize = new Serialize(users);
            objOStr.writeObject(serialize);
            objOStr.flush();
        }
        catch (IOException e){
            new RuntimeException(e);
        }
    }

    public static Collection<User> deSerialize(String path) {
        Serialize serialize = new Serialize();
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            serialize = (Serialize) objectInputStream.readObject();

        }
        catch (IOException e){
            new RuntimeException(e);
        }
        catch (ClassNotFoundException e){
            new RuntimeException(e);
        }
        return serialize.getUsers();
    }

    public Serialize() {
    }

    public Serialize(Collection<User> users) {
        this.users = users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

}
