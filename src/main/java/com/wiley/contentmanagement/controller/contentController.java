/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.Tag;
import com.wiley.contentmanagement.service.ArticleService;
import com.wiley.contentmanagement.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.HashMap;

/**
 *
 * @author erres
 */
@Controller
public class contentController {

    @Autowired
    ArticleService aService;

    @Autowired
    ArticleTagService atService;

    @GetMapping({"/index","/"})
    public String index(Model model){
        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllArticles();

        // get article tags by aid from atS
        for(Article article : blogs){
            int temp= article.getAid();
            atmap.put(temp,atService.getArticleTagByAid(temp));
        }

        model.addAttribute("blogs",blogs);
        model.addAttribute("atmap",atmap);
        return "/index.html";
    }

    @GetMapping("/blogPage")
    public String getBlogPage(@RequestParam("aid") int aid,Model model){
        model.addAttribute("blog",aService.getArticleById(aid));
        return "/blogDetail.html";
    }

}
