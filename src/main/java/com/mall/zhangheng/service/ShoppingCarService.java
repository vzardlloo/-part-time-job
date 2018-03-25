package com.mall.zhangheng.service;

import com.mall.zhangheng.domain.ShoppingCar;
import com.mall.zhangheng.repository.ShoppingCarRepository;
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


    public ShoppingCar updateOrder(ShoppingCar shoppingCar) {
        ShoppingCar shoppingCar1 = shoppingCarRepository.saveAndFlush(shoppingCar);
        return shoppingCar1;
    }

    public List<ShoppingCar> getOrderList() {
        List<ShoppingCar> shoppingCarList = shoppingCarRepository.findAll();
        return shoppingCarList;
    }


}
