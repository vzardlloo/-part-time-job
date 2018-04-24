package com.mall.zhangxuan.service;

import com.mall.zhangxuan.domain.Customer;
import com.mall.zhangxuan.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){

        return customerRepository.findAll();
    }

    public Customer getCustomerByNameAndPassword(String name,String password){
        return customerRepository.getCustomerByNameAndPassword(name, password);
    }

    public Customer savaCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

}