/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Article;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author erres
 */
@Repository
public class ArticleDaoDB implements ArticleDao{
    @Autowired
    JdbcTemplate jdbc;


    @Override
    public Article getArticleById(int aid) {
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        return null;
    }

    @Override
    public Article addArtricle(Article article) {
        return null;
    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticleById(int aid) {

    }
}
