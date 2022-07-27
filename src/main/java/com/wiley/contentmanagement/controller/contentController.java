/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author erres
 */
@Controller
public class contentController {

       @GetMapping("page")
        public String displayPage(Model model) {

        return "page";
       }
        
       @GetMapping("login")
        public String displayLogin(Model model) {

        return "login";
       }
                
       @GetMapping("editpage")
        public String editPage(Model model) {

        return "editpage";
       }
        
       @GetMapping("manage")
        public String displayManage(Model model) {

        return "manage";
       }
    
}
