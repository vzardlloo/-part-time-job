package com.mall.zhangheng.controller;

import com.mall.zhangheng.domain.User;
import com.mall.zhangheng.dto.UserLoginParam;
import com.mall.zhangheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "dashboard")
public class DashBoardController {



    private ModelAndView modelAndView = new ModelAndView();
    @Autowired
    UserService userService;

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





}
