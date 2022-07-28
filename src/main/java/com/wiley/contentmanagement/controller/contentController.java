/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author erres
 */
@Controller
public class contentController {

    @Autowired
    ArticleService aService;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("blogs",aService.getAllArticles());
        return "/index.html";
    }

}
