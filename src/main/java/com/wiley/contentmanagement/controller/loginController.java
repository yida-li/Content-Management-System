package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.User;
import com.wiley.contentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Enumeration;
import java.util.HashMap;

@Controller
public class loginController {

    @Autowired
    UserService uService;

    @GetMapping("/login")
    public String getLogin(){
        return "/login.html";
    }

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, HttpServletResponse res, Model model){
        Enumeration<String> names = request.getParameterNames();
        HashMap<String,String> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            if(values!=null){
                map.put(name, values[0]);
            }
        }

        List<User> users = uService.getUserByName(map.get("username"));

        User result = new User();
        boolean flag = false;


        for (User user : users) {
            if(user.getPassword().equals(map.get("password"))){
                result=user;
                flag=true;
            }
        }
        HttpSession session = request.getSession();
        if(flag){

            session.setAttribute("user",result);
            session.setAttribute("role",result.getRole());
            session.setAttribute("loginmsg","login succeeded!");
            return "redirect:/";
        }else{
            session.setAttribute("loginmsg","login failed!");
            return "/login.html";
        }

    }

    @RequestMapping({"/404","/500"})
    public String errorPage(){
        return "/404.html";
    }
}
