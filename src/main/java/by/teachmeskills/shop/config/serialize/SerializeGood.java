package by.teachmeskills.shop.config.serialize;

import by.teachmeskills.shop.entity.Good;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

public class SerializeGood implements Serializable{
    private Collection<Good>  goods = new LinkedList<>();

    public static void serialize(Collection<Good> goods, String path){
        try(ObjectOutputStream objOStr = new ObjectOutputStream(new FileOutputStream(path, false))){
            SerializeGood serializeGood = new SerializeGood(goods);
            objOStr.writeObject(serializeGood);
            objOStr.flush();
        }
        catch (IOException e){
            new RuntimeException(e);
        }
    }


    public static Collection<Good> deSerialize(String path) {
        SerializeGood serializeGood = new SerializeGood();
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            serializeGood = (SerializeGood) objectInputStream.readObject();

        }
        catch (IOException | ClassNotFoundException e){
            new RuntimeException(e);
        }
        return serializeGood.getGoods();
    }

    public Collection<Good> getGoods() {
        return goods;
    }

    public void setGoods(Collection<Good> goods) {
        this.goods = goods;
    }
    public SerializeGood(Collection<Good> goods) {
        this.goods = goods;
    }
    public SerializeGood() {
    }

}
