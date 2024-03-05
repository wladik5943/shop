package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.entity.ShopEntity;
import by.teachmeskills.shop.entity.User;

import java.util.Collection;

public class GoodRepository implements ShopRepository{
    @Override
    public void add(ShopEntity ent) {
        Good good = (Good)ent;
        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
        good.setId(goods.size() + 1);
        goods.add(good);
        SerializeGood.serialize(goods,"D:\\progects\\shop\\src\\main\\resources\\goods");
    }

    @Override
    public void deleteByld(int userId) {
        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
        for(Good temp: goods){
            if(temp.getId() == userId) {
                goods.remove(temp);
                break;
            }
        }
        SerializeGood.serialize(goods,"D:\\progects\\shop\\src\\main\\resources\\goods");
    }

    public Collection<Good> all() {
        return SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
    }
}
