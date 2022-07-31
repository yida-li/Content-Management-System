/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.Tag;
import com.wiley.contentmanagement.model.User;
import com.wiley.contentmanagement.service.ArticleService;
import com.wiley.contentmanagement.service.ArticleTagService;
import com.wiley.contentmanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author erres
 */
@Controller
public class ArticleController {

    @Autowired
    ArticleService aService;

    @Autowired
    ArticleTagService atService;

    @Autowired
    TagService tService;

    @GetMapping({"/index","/"})
    public String index(Model model){
        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllDisplayArticles();

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

    @GetMapping("/getArticlesByTag")
    public ResponseEntity getArticlesByTag(Model model){
        List<Tag> tagList = tService.getAllTags();
        HashMap<Integer,List<Article>> articleMap = new HashMap<>();
        for(Tag tag : tagList){
            articleMap.put(tag.getTid(), atService.getArticlesByTid(tag.getTid()));
        }
        model.addAttribute("articleMap",articleMap);
        return ResponseEntity.ok().body(articleMap);
    }

    @PostMapping("/updateArticleTag")
    public ResponseEntity postChangeTag(HttpServletRequest req,
                                        @RequestParam("id") int aid){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }
        List<Integer> list = new ArrayList<>();
        // name from frontend
        if(map.get("tagList")!=null){
            for (String key : map.get("tagList")) {
                list.add(Integer.parseInt(key));
            }
        }
        atService.updateArticleTag(aid,list);
        return ResponseEntity.ok().body(list);
    }

}
