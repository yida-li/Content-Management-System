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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
        model.addAttribute("tags", tagService.getAllTags());
        return "page";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllArticles();

        // get article tags by aid from atS
        for (Article article : blogs) {
            int temp = article.getAid();
            atmap.put(temp, atService.getArticleTagByAid(temp));
        }

        model.addAttribute("blogs", blogs);
        model.addAttribute("atmap", atmap);

        return "manage";
    }

    @GetMapping("/manageBoss")
    public String manageBoss(Model model) {
        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllDrafts();

        // get article tags by aid from atS
        for (Article article : blogs) {
            int temp = article.getAid();
            atmap.put(temp, atService.getArticleTagByAid(temp));
        }

        model.addAttribute("blogs", blogs);
        model.addAttribute("atmap", atmap);

        return "manageBoss";
    }

    @GetMapping("/tag")
    public String tag(Model model) {
        HashMap<Integer, List<Tag>> atmap = new HashMap<>();
        List<Article> blogs = aService.getAllArticles();

        List<Tag> tagList = tagService.getAllTags();

        HashMap<Integer, List<Article>> articleMap = new HashMap<>();

        for (Tag tag : tagList) {
            articleMap.put(tag.getTid(), atService.getArticlesByTid(tag.getTid()));
        }

        // get article tags by aid from atS
        for (Article article : blogs) {
            int temp = article.getAid();
            atmap.put(temp, atService.getArticleTagByAid(temp));
        }
        model.addAttribute("articleMap", articleMap);
        model.addAttribute("blogs", blogs);
        model.addAttribute("atmap", atmap);
        model.addAttribute("tags", tagService.getAllTags());
        return "tag";
    }

    @GetMapping("/manage/editBlog")
    public String editPage(int aid, Model model) {
        model.addAttribute("article", articleService.getArticleById(aid));
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("selected", atService.getArticleTagByAid(aid).stream().mapToInt(Tag::getTid).boxed().collect(Collectors.toList()));

        return "editpage";
    }

    @PostMapping("/manage/editBlog")
    public String performEdit(int aid, String title, String content, Integer[] tid, String date, String time) {
        atService.deleteArticleTagByAid(aid);

        Article article = articleService.getArticleById(aid);
        article.setTitle(title);
        article.setContent(content);
        article.setUpdateTime(LocalDateTime.now());
        article.setDisplay(0);

        if(date!=null&&time != null){
            String datetime = date+" "+time;
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime ldt = LocalDateTime.parse(datetime,df);

            article.setExpireTime(ldt);
        }

        articleService.updateArticle(article);

        if (tid != null) {
            for (Integer t : tid) {
                ArticleTag at = new ArticleTag();
                at.setArticle(article);
                at.setTag(tagService.getTagById(t));
                articleService.addTag(at);
            }
        }

        return "redirect:/manage";
    }

    @GetMapping("/manage/deleteBlog")
    public String deletePage(int aid) {
        articleService.deleteArticleById(aid);
        return "redirect:/manage";
    }

    
    
    @GetMapping("/manageBoss/deleteBlog")
    public String deleteBossPage(int aid) {
        articleService.deleteArticleById(aid);
        return "redirect:/manageBoss";
    }
    
    @GetMapping("/manageBoss/approveBlog")
    public String approvePage(int aid) {
        articleService.approveArticle(aid);
        return "redirect:/manageBoss";
    }
    
    
    @PostMapping("/tinytxt")
    public String writeBlog(String title, String tinyContent, Integer[] tid) {

        //article objectr
        Article a = new Article();

        a.setTitle(title);
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
