package com.mall.zhangxuan.repository;


import com.mall.zhangxuan.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

        Customer getCustomerByNameAndPassword(String name,String password);

}
