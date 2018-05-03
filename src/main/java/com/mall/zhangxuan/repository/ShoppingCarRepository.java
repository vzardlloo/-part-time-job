package com.mall.zhangxuan.repository;


import com.mall.zhangxuan.domain.ShoppingCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCarRepository extends JpaRepository<ShoppingCar,Integer> {


    List<ShoppingCar> getShoppingCarsByCid(String cid);

    ShoppingCar getShoppingCarById(Integer id);
}
