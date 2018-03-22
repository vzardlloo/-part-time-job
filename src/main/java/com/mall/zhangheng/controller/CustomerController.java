package com.mall.zhangheng.controller;


import com.mall.zhangheng.domain.Customer;
import com.mall.zhangheng.domain.User;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.dto.UserLoginParam;
import com.mall.zhangheng.service.CustomerService;
import com.mall.zhangheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("dashboard")
public class CustomerController {

    @Autowired
    CustomerService customerService;

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
        Customer newcustomer = customerService.savaCustomer(customer);
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
