package com.mall.zhangheng.controller;

import com.mall.zhangheng.domain.Message;
import com.mall.zhangheng.domain.ShoppingCar;
import com.mall.zhangheng.domain.User;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.dto.UserLoginParam;
import com.mall.zhangheng.repository.MessageRepository;
import com.mall.zhangheng.repository.ShoppingCarRepository;
import com.mall.zhangheng.service.ShoppingCarService;
import com.mall.zhangheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "dashboard")
public class DashBoardController {



    private ModelAndView modelAndView = new ModelAndView();
    @Autowired
    UserService userService;
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    ShoppingCarRepository shoppingCarRepository;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response, UserLoginParam user){
        HttpSession session = request.getSession();
        //ModelAndView modelAndView = new ModelAndView();
        String verifyCode = (String) session.getAttribute("verifyCode");
        User currentUser = userService.getUserByNameAndPassword(user.getName(),user.getPassword());
        if (currentUser != null && verifyCode.equals(user.getVerifycode())) {
            modelAndView.setViewName("dashboard/main");
            modelAndView.addObject("user",currentUser);
            modelAndView.addObject("body",new String("body"));
            return modelAndView;
        }else {
            request.getSession().invalidate();
            modelAndView.setViewName("redirect:/dashboard/login");
        }
        return null;
    }

    @RequestMapping(value = "/new-customer",method = RequestMethod.GET)
    public ModelAndView newCustomer(){
        //ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dashboard/main");

        modelAndView.addObject("body", new String("customer/new_customer"));

        return modelAndView;

    }


    @RequestMapping(value = "/all-customer",method = RequestMethod.GET)
    public ModelAndView Customers(){

        //ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("body", new String("customer/all_customer"));
        modelAndView.setViewName("dashboard/main");


        return modelAndView;

    }


    @RequestMapping(value = "/all-product",method = RequestMethod.GET)
    public ModelAndView Products(){

        //ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("body", new String("product/all_product"));
        modelAndView.setViewName("dashboard/main");


        return modelAndView;

    }

    @RequestMapping(value = "/new-product",method = RequestMethod.GET)
    public ModelAndView newProduct(){
        //ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dashboard/main");

        modelAndView.addObject("body", new String("product/new_product"));

        return modelAndView;

    }

    @RequestMapping(value = "/all-order", method = RequestMethod.GET)
    public ModelAndView allOrder() {
        //ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dashboard/main");

        modelAndView.addObject("body", new String("order/all-order"));

        return modelAndView;

    }

    @RequestMapping(value = "/all-message", method = RequestMethod.GET)
    public ModelAndView allMessage() {
        //ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dashboard/main");

        modelAndView.addObject("body", new String("message/all-message"));

        return modelAndView;

    }

    @RequestMapping(value = "/new-message", method = RequestMethod.GET)
    public ModelAndView newMessage() {
        //ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dashboard/main");

        modelAndView.addObject("body", new String("message/new-message"));

        return modelAndView;
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

    @RequestMapping(value = "/orders", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseModel delOrders(@RequestBody ShoppingCar shoppingCar) {
        shoppingCarRepository.delete(shoppingCar);
        return ResponseModel.builder()
                .code(0)
                .build();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getOrders(@RequestBody ShoppingCar shoppingCar) {
        ShoppingCar shoppingCar1 = shoppingCarService.saveOrder(shoppingCar);
        return ResponseModel.builder()
                .code(0)
                .data(shoppingCar1)
                .build();
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel saveMessage(@RequestBody Message message) {
        Message message1 = messageRepository.saveAndFlush(message);
        return ResponseModel.builder()
                .code(0)
                .data(message1)
                .build();
    }

    @RequestMapping(value = "/message", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseModel delMessage(@RequestBody Message message) {
        messageRepository.delete(message);
        return ResponseModel.builder()
                .code(0)
                .build();
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getMessages() {
        List<Message> messages = messageRepository.findAll();
        return ResponseModel.builder()
                .code(0)
                .data(messages)
                .build();
    }







}
