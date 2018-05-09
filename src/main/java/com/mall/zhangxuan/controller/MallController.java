package com.mall.zhangxuan.controller;

import com.mall.zhangxuan.domain.*;
import com.mall.zhangxuan.dto.ResponseModel;
import com.mall.zhangxuan.repository.MessageRepository;
import com.mall.zhangxuan.repository.ShoppingCarRepository;
import com.mall.zhangxuan.service.CustomerService;
import com.mall.zhangxuan.service.ProductService;
import com.mall.zhangxuan.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value = "/mall", method = RequestMethod.GET)
    public ModelAndView mall(@RequestParam(value = "name", required = false) Optional<String> name) {
        System.out.println(name);
        List<Product> productList;
        if (name.equals(Optional.empty())) {
            productList = productService.getProducts();
        } else {
            productList = productService.getProductsByName(name.get());

        }

        List<Product> products = productService.getProducts();
        modelAndView.addObject("hotproduct", products);
        modelAndView.addObject("product",productList);
        modelAndView.setViewName("mall/index");
        return modelAndView;
    }






    @RequestMapping(value = "/shop",method = RequestMethod.GET)
    public ModelAndView shop(HttpServletRequest request, @RequestParam(value = "name", required = false) Optional<String> name) {
        ModelAndView modelAndView = new ModelAndView();
        Customer user = (Customer) request.getSession().getAttribute("currentUser");
        if (user != null) {
            modelAndView.addObject("user", user);
            List<Product> productList;
            if (name.equals(Optional.empty())) {
                productList = productService.getProducts();
            } else {
                productList = productService.getProductsByName(name.get());

            }
            modelAndView.addObject("product", productList);
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
        Customer newcustomer = customerService.saveCustomer(customer);
        return "mall/regist";
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String getShoppingCar() {
        return "/mall/checkout";
    }

    @RequestMapping(value = "/payment.html",method = {RequestMethod.GET,RequestMethod.POST})
    public String payment(){
        return "mall/payment";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "name", required = false) Optional<String> name) {

        List<Product> productList;
        if (name.equals(Optional.empty())) {
            productList = productService.getProducts();
        } else {
            productList = productService.getProductsByName(name.get());

        }

        List<Product> products = productService.getProducts();
        modelAndView.addObject("hotproduct", products);
        modelAndView.addObject("product",productList);
        modelAndView.setViewName("mall/index");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, Customer customer) {
        //ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        List<Product> productList;

        productList = productService.getProducts();


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
        //List<Product> productList = productService.getProducts();
        session.setAttribute("products", productList);
        modelAndView.addObject("msg", newMassage);
        modelAndView.addObject("product", productList);
        List<Product> products = productService.getProducts();
        modelAndView.addObject("hotproduct", products);
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


    @RequestMapping(value = "/mall-logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        modelAndView.clear();
        ModelAndView modelAndView = new ModelAndView("redirect:/mall");
        //modelAndView.setViewName("mall/index");
        return modelAndView;
    }



    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ModelAndView shoppingCar(ShoppingCar shoppingCar, HttpServletRequest request) {
        ShoppingCar shoppingCar1 = shoppingCarService.saveOrder(shoppingCar);

        ModelAndView modelAndView = new ModelAndView("redirect:/checkout");

        return modelAndView;


    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkOut(HttpServletRequest request) {

        Customer user = (Customer) request.getSession().getAttribute("currentUser");
        List<ShoppingCar> shoppingCarList = shoppingCarService.getOrderList(user.getId().toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orderList", shoppingCarList);
        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/mall/checkout");
            return modelAndView;
        } else {
            modelAndView.setViewName("mall/to-login");
            return modelAndView;
        }
    }



    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getOrders(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Customer customer = (Customer) httpSession.getAttribute("currentUser");
        List<ShoppingCar> shoppingCarList = shoppingCarService.getOrderList(customer.getId().toString());
        return ResponseModel.builder()
                .code(0)
                .data(shoppingCarList)
                .build();
    }

    @RequestMapping(value = "/pay", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateOrder(@RequestParam("id") Integer id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Customer user = (Customer) request.getSession().getAttribute("currentUser");
        ShoppingCar shoppingCar = shoppingCarService.getShoppingCarById(id);
        if (shoppingCar.getPayment().equals("已支付")) {
            modelAndView.setViewName("/mall/pay-error");
        } else {
            shoppingCar.setPayment("已支付");
            shoppingCarRepository.saveAndFlush(shoppingCar);
//            Integer scoreInt =  Integer.parseInt(user.getScore())+Integer.parseInt(shoppingCar.getValue());
//            String  score = scoreInt.toString();
//            user.setScore(score);
//            customerService.saveCustomer(user);
            modelAndView.addObject("order", shoppingCar);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/mall/pay-success");
        }
        return modelAndView;
    }




}
