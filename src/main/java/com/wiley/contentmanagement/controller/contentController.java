/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.service.ArticleService;
import com.wiley.contentmanagement.service.TagService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author erres
 */
@Controller
public class contentController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/page")
    public String page(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "page";
    }

    @GetMapping("/manage")
    public String manage() {
        return "manage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/editpage")
    public String editPage() {
        return "editpage";
    }

    @PostMapping("/tinytxt")
    public String writeBlog(HttpServletRequest request) {

        Article a = new Article();
        a.setContent(request.getParameter("tinyContent"));
        a.setCreateTime(LocalDateTime.now());

        articleService.addArtricle(a);

        for (String tid : request.getParameterValues("tid")) {
            articleService.addTag(a, Integer.parseInt(tid));
        }

        return "redirect:/";
    }

}
