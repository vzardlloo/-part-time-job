package com.mall.zhangheng.controller;

import com.mall.zhangheng.domain.Customer;
import com.mall.zhangheng.domain.Product;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.service.CustomerService;
import com.mall.zhangheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MallController {

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/mall",method = RequestMethod.GET)
    public ModelAndView mall(){
        List<Product> productList = productService.getAllProducts();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("product",productList);
        modelAndView.setViewName("mall/index");
        return modelAndView;
    }

    @RequestMapping(value = "/shop",method = RequestMethod.GET)
    public String shop(){
        return "mall/shop";
    }

    @RequestMapping(value = "/checkout.html",method = {RequestMethod.POST,RequestMethod.GET})
    public String checkout(){
        return "mall/checkout";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(Customer customer){
        Customer newcustomer = customerService.savaCustomer(customer);
        return "mall/regist";
    }

    @RequestMapping(value = "/payment.html",method = {RequestMethod.GET,RequestMethod.POST})
    public String payment(){
        return "mall/payment";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        Customer existCustomer = customerService.getCustomerByNameAndPassword(customer.getName(),customer.getPassword());
        System.out.println(existCustomer.getName());
        if (existCustomer != null){

            modelAndView.addObject("user",existCustomer);
            modelAndView.setViewName("/mall/index");
            return modelAndView;
        }else {
            modelAndView.setViewName("");
            return modelAndView;
        }
    }

}
