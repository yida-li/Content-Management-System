/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.dao.ArticleDao;
import com.wiley.contentmanagement.model.Article;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author erres
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public Article getArticleById(int aid) {
        return articleDao.getArticleById(aid);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Override
    public Article addArtricle(Article article) {
        return articleDao.addArtricle(article);
    }

    @Override
    public void updateArticle(Article article) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteArticleById(int aid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addTag(Article a, int tid) {
        articleDao.addTag(a.getAid(), tid);
    }

}
