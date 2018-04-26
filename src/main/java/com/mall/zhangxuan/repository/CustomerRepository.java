package com.mall.zhangxuan.repository;


import com.mall.zhangxuan.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

        List<Customer> getCustomerByNameAndPassword(String name, String password);

}
