package com.mall.zhangheng.service;

import com.mall.zhangheng.domain.Customer;
import com.mall.zhangheng.repository.CustomerRepository;
import com.mall.zhangheng.repository.UserRepository;
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
