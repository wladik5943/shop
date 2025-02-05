package by.teachmeskills.shop.mapper;

import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.entity.Good;

public class GoodMapper {

    public Good toEntity(GoodRequest goodRequest){
        Good good = new Good();
        good.setName(goodRequest.getName());
        good.setCode(goodRequest.getCode());
        good.setSubtype(goodRequest.getSubtype());
        good.setPrice(goodRequest.getPrice());
        good.setQuantity(goodRequest.getQuantity());
        return good;
    }

    public GoodResponse toGoodResponse(Good good){
        GoodResponse goodResponse = new GoodResponse();
        goodResponse.setId(good.getId());
        goodResponse.setName(good.getName());
        goodResponse.setCode(good.getCode());
        goodResponse.setSubtype(good.getSubtype());
        goodResponse.setPrice(good.getPrice());
        goodResponse.setQuantity(good.getQuantity());
        return goodResponse;
    }

}

