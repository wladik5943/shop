package by.teachmeskills.shop.service;

import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.mapper.GoodMapper;
import by.teachmeskills.shop.repository.goodRepository.impl.GoodRepositoryJDBC;

import java.util.Collection;

public class GoodService {

    public Collection<Good> all(){
        by.teachmeskills.shop.repository.goodRepository.GoodRepository goodRepository = new GoodRepositoryJDBC();
        return goodRepository.all();
    }
    public GoodResponse goodAdd(GoodRequest goodRequest){
        GoodMapper goodMapper = new GoodMapper();
        by.teachmeskills.shop.repository.goodRepository.GoodRepository goodRepository = new GoodRepositoryJDBC();
        Good good = goodMapper.toEntity(goodRequest);
        goodRepository.add(good);
        return goodMapper.toGoodResponse(good);
    }
}
