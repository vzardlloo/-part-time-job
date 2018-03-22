package com.mall.zhangheng.repository;


import com.mall.zhangheng.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

        Customer getCustomerByNameAndPassword(String name,String password);

}
