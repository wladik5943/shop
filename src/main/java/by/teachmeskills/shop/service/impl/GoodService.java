package by.teachmeskills.shop.service.impl;

import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.mapper.GoodMapper;
import by.teachmeskills.shop.repository.GoodRepository;

public class GoodService {
    public GoodResponse goodAdd(GoodRequest goodRequest){
        GoodMapper goodMapper = new GoodMapper();
        GoodRepository goodRepository = new GoodRepository();
        Good good = goodMapper.toEntity(goodRequest);
        goodRepository.add(good);
        return goodMapper.toGoodResponse(good);
    }
}
