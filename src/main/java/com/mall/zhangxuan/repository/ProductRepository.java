package com.mall.zhangxuan.repository;


import com.mall.zhangxuan.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findAllByNameIsLike(String name);

}
