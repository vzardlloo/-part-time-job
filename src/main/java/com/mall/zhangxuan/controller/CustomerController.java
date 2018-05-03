package com.mall.zhangxuan.controller;


import com.mall.zhangxuan.domain.Customer;
import com.mall.zhangxuan.dto.ResponseModel;
import com.mall.zhangxuan.repository.CustomerRepository;
import com.mall.zhangxuan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("dashboard")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @ResponseBody
    @RequestMapping(value = "/customers",method = RequestMethod.GET)
    public ResponseModel<List<Customer>> getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomer();
        return ResponseModel.builder()
                .code(0)
                .data(customerList)
                .build();

    }

    @ResponseBody
    @RequestMapping(value = "/customers",method = RequestMethod.POST)
    public ResponseModel<Customer> saveCustomer(@RequestBody Customer customer){
        Customer newcustomer = customerRepository.saveAndFlush(customer);
        return ResponseModel.builder()
                .code(0)
                .data(newcustomer)
                .build();

    }

    @ResponseBody
    @RequestMapping(value = "/customers",method = RequestMethod.DELETE)
    public ResponseModel deleteCustomer(@RequestBody Customer customer,HttpServletResponse response){
        customerService.deleteCustomer(customer);
        return ResponseModel.builder()
                .code(response.getStatus())
                .message("Success")
                .build();

    }

}
