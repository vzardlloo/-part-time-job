package com.mall.zhangheng.controller;

import com.mall.zhangheng.domain.*;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.repository.MessageRepository;
import com.mall.zhangheng.repository.ShoppingCarRepository;
import com.mall.zhangheng.service.CustomerService;
import com.mall.zhangheng.service.ProductService;
import com.mall.zhangheng.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MallController {

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    ShoppingCarRepository shoppingCarRepository;
    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(value = "/mall",method = RequestMethod.GET)
    public ModelAndView mall(){
        List<Product> productList = productService.getAllProducts();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("product",productList);
        modelAndView.setViewName("mall/index");
        return modelAndView;
    }

    @RequestMapping(value = "/shop",method = RequestMethod.GET)
    public ModelAndView shop(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Customer user = (Customer) request.getSession().getAttribute("currentUser");
        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.addObject("product", request.getSession().getAttribute("products"));
            modelAndView.setViewName("mall/shop");
            return modelAndView;
        } else {
            modelAndView.setViewName("mall/to-login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/checkout.html",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView checkout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", request.getSession().getAttribute("currentUser"));
        modelAndView.addObject("product", request.getSession().getAttribute("products"));
        modelAndView.setViewName("mall/checkout");
        return modelAndView;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, Customer customer) {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = request.getSession();

        List<Message> messageList = messageRepository.findAll();
        LinkedList<Message> messages = new LinkedList<>();
        messages.addAll(messageList);

        Message newMassage = messages.getLast();
        if (newMassage == null) {
            newMassage = new Message();
        }
        System.out.println(newMassage.getTitle());
        Customer existCustomer = customerService.getCustomerByNameAndPassword(customer.getName(),customer.getPassword());
        List<Product> productList = productService.getAllProducts();
        session.setAttribute("products", productList);
        modelAndView.addObject("msg", newMassage);
        modelAndView.addObject("product", productList);
        //System.out.println(existCustomer.getName());
        if (existCustomer != null){
            session.setAttribute("currentUser", existCustomer);
            modelAndView.addObject("user",existCustomer);
            modelAndView.setViewName("/mall/index");
            return modelAndView;
        }else {
            modelAndView.setViewName("/mall/to-login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ModelAndView shoppingCar(ShoppingCar shoppingCar, HttpServletRequest request) {
        ShoppingCar shoppingCar1 = shoppingCarService.saveOrder(shoppingCar);
        List<ShoppingCar> shoppingCarList = shoppingCarService.getOrderList();
        Customer user = (Customer) request.getSession().getAttribute("currentUser");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orderList", shoppingCarList);
        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/mall/shopping-car");
            return modelAndView;
        } else {
            modelAndView.setViewName("mall/to-login");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String getShoppingCar() {
        return "/mall/shopping-car";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getOrders() {
        List<ShoppingCar> shoppingCarList = shoppingCarService.getOrderList();
        return ResponseModel.builder()
                .code(0)
                .data(shoppingCarList)
                .build();
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel updateOrder(@RequestBody ShoppingCar shoppingCar) {
        shoppingCar.setPayment("已支付");
        ShoppingCar shoppingCar1 = shoppingCarRepository.saveAndFlush(shoppingCar);
        return ResponseModel.builder()
                .code(0)
                .data(shoppingCar1)
                .build();
    }




}
