/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.Tag;
import com.wiley.contentmanagement.model.Draft;
import com.wiley.contentmanagement.service.ArticleService;
import com.wiley.contentmanagement.service.ArticleTagService;
import com.wiley.contentmanagement.service.DraftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author erres
 */
@Controller
public class contentController {

    @Autowired
    DraftService dService;





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

    
    
    
        @GetMapping({"/draftPage"})
    public String draftManage(Model model){
        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Draft> blogs = dService.getAllDrafts();

        // get Draft tags by aid from atS
        for(Draft article : blogs){
            int temp= article.getAid();
            atmap.put(temp,atService.getArticleTagByAid(temp));
        }

        model.addAttribute("blogs",blogs);
        model.addAttribute("atmap",atmap);
        return "/draftPage.html";
    }

    
    
    
    
    
    @GetMapping("/blogPage")
    public String getBlogPage(@RequestParam("aid") int aid,Model model){
        model.addAttribute("blog",aService.getArticleById(aid));
        return "/blogDetail.html";
    }
    
    
    
    
    
    

    
    
    
    
    
    /*
    remaining request end points to 
    */
    
    
    
    @GetMapping("/editpage")
    public String editPage(Model model){
        
        return "editpage";
    }
    @GetMapping("/page")
    public String page(Model model){
        
        return "page";
    }
    @GetMapping("/manage")
    public String manage(Model model){
                HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllArticles();

        // get article tags by aid from atS
        for(Article article : blogs){
            int temp= article.getAid();
            atmap.put(temp,atService.getArticleTagByAid(temp));
        }

        model.addAttribute("blogs",blogs);
        model.addAttribute("atmap",atmap);
        return "manage";
    }
    @GetMapping("/login")
    public String getAllLocations(Model model){
        
        return "login";
    }

}
