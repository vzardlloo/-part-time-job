package com.mall.zhangxuan.service;

import com.mall.zhangxuan.domain.ShoppingCar;
import com.mall.zhangxuan.repository.ShoppingCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCarService {

    @Autowired
    ShoppingCarRepository shoppingCarRepository;

    public ShoppingCar saveOrder(ShoppingCar shoppingCar) {
        ShoppingCar shoppingCar1 = shoppingCarRepository.save(shoppingCar);
        return shoppingCar1;
    }

    public ShoppingCar getShoppingCarById(Integer id) {
        return shoppingCarRepository.getShoppingCarById(id);
    }


    public ShoppingCar updateOrder(ShoppingCar shoppingCar) {
        ShoppingCar shoppingCar1 = shoppingCarRepository.saveAndFlush(shoppingCar);
        return shoppingCar1;
    }

    public List<ShoppingCar> getOrderList(String cid) {
        List<ShoppingCar> shoppingCarList = shoppingCarRepository.getShoppingCarsByCid(cid);
        return shoppingCarList;
    }

    public List<ShoppingCar> getOrderList() {
        List<ShoppingCar> shoppingCarList = shoppingCarRepository.findAll();
        return shoppingCarList;
    }


}
