package by.teachmeskills.shop.repository.goodRepository.impl;

import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.repository.goodRepository.GoodRepository;
import by.teachmeskills.shop.config.serialize.SerializeGood;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GoodRepositorySerialize implements GoodRepository {
    @Override
    public void add(Good good) {
        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
        good.setId(goods.size() + 1);
        goods.add(good);
        SerializeGood.serialize(goods,"D:\\progects\\shop\\src\\main\\resources\\goods");
    }

    @Override
    public void deleteById(int id) {
        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
        for (Good temp : goods) {
            if (temp.getId() == id) {
                goods.remove(temp);
                break;
            }
        }
        SerializeGood.serialize(goods, "D:\\progects\\shop\\src\\main\\resources\\goods");
    }

    @Override
    public Collection<Good> all() {
        return SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
    }

    @Override
    public int priceGood(int goodId) {
        return 0;
    }

    @Override
    public void returnGoodFromBasket(int goodId, int quantity) {

    }

    @Override
    public void buyGood(int goodId, int quantity) {

    }

    @Override
    public Collection<Good> listGetGoodsById(List<Integer> goodIds) {
        Collection<Good> goods = new LinkedList<>();
        goodIds.forEach(x -> {
            goods.add(searchById(x));
        });
        return goods;
    }

    @Override
    public Good searchById(int id) {
        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
        for (Good temp : goods)
            if (temp.getId() == id)
                return temp;
        return null;
    }
}
