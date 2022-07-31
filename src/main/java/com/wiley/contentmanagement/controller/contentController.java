/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.ArticleTag;
import com.wiley.contentmanagement.service.ArticleService;
import com.wiley.contentmanagement.service.TagService;
import java.time.LocalDateTime;
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

    @GetMapping("/page")
    public String page(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "page";
    }

    @GetMapping("/manage")
    public String manage() {
        return "manage";
    }



    @GetMapping("/editpage")
    public String editPage() {
        return "editpage";
    }

    @PostMapping("/tinytxt")
    public String writeBlog(String tinyContent, Integer[] tid) {

        Article a = new Article();
        a.setContent(tinyContent);
        a.setCreateTime(LocalDateTime.now());

        articleService.addArtricle(a);

        if (tid != null) {
            for (Integer t : tid) {
                ArticleTag at = new ArticleTag();
                at.setArticle(a);
                at.setTag(tagService.getTagById(t));
                articleService.addTag(at);
            }
        }

        return "redirect:/";
    }

}
