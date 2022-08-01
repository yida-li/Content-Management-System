/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.ArticleTag;
import com.wiley.contentmanagement.model.Tag;
import com.wiley.contentmanagement.service.ArticleService;
import com.wiley.contentmanagement.service.ArticleTagService;
import com.wiley.contentmanagement.service.TagService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    
    @Autowired
    ArticleService aService;

    @Autowired
    ArticleTagService atService;
    
    @GetMapping("/page")
    public String page(Model model) {
        
        return "page";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
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

     @GetMapping("/manageBoss")
    public String manageBoss(Model model) {
       HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllDrafts();

        // get article tags by aid from atS
        for(Article article : blogs){
            int temp= article.getAid();
            atmap.put(temp,atService.getArticleTagByAid(temp));
        }

        model.addAttribute("blogs",blogs);
        model.addAttribute("atmap",atmap);
        
        return "manageBoss";
    }
    
    
    
       @GetMapping("/tag")
    public String tag(Model model) {
                        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllArticles();
        
        List<Tag> tagList = tagService.getAllTags();
        
        HashMap<Integer,List<Article>> articleMap = new HashMap<>();
        
        for (Tag tag: tagList){
            articleMap.put(tag.getTid(),atService.getArticlesByTid(tag.getTid()));
        }
        
        // get article tags by aid from atS
        
        for(Article article : blogs){
            int temp= article.getAid();
            atmap.put(temp,atService.getArticleTagByAid(temp));
        }
        model.addAttribute("articleMap",articleMap);
        model.addAttribute("blogs",blogs);
        model.addAttribute("atmap",atmap);
        model.addAttribute("tags", tagService.getAllTags());
        return "tag";
    }


    @GetMapping("/editpage")
    public String editPage() {
        return "editpage";
    }

    @PostMapping("/tinytxt")
    public String writeBlog(String title, String tag,String tinyContent, Integer[] tid) {

        //article objectr
        Article a = new Article();
      
        a.setTitle(title);
        a.setContent(tinyContent);
        
        a.setCreateTime(LocalDateTime.now());
        a.setUpdateTime(LocalDateTime.now());
        articleService.addArtricle(a);
        
        //tag object
        Tag tt= new Tag();
        tt.setName(tag);
        tagService.addTag(tt);
        // articletag object 
        ArticleTag att = new ArticleTag();
        att.setArticle(a);
        att.setTag(tt);
        articleService.addTag(att);
        
        /*
           if (tid != null) {
            for (Integer t : tid) {
                ArticleTag at = new ArticleTag();
                at.setArticle(a);
                at.setTag(tagService.getTagById(t));
                articleService.addTag(at);
            }
        }
        
        */
     

        return "redirect:/";
    }

}
