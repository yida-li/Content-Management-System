/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.dao.ArticleDao;
import com.wiley.contentmanagement.dao.ArticleTagDao;
import com.wiley.contentmanagement.model.Article;
import com.wiley.contentmanagement.model.ArticleTag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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

    @Autowired
    ArticleTagDao articleTagDao;

    @Override
    public Article getArticleById(int aid) {
        return articleDao.getArticleById(aid);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Override
    public List<Article> getAllDrafts() {
        return articleDao.getAllDraft();
    }

    @Override
    public Article addArtricle(Article article) {
        return articleDao.addArtricle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public void deleteArticleById(int aid) {
        articleDao.deleteArticleById(aid);
    }

    @Override
    public void addTag(ArticleTag articleTag) {
        articleTagDao.addArtricleTag(articleTag);
    }

    @Override
    public void approveArticle(int aid) {
        articleDao.approveArticle(aid);
    }

    @Override
    public List<Article> getAllDisplayArticles() {
        return articleDao.getAllArticles()
                .stream()
                .filter(a -> (
                            (a.getDisplay() == 1)
                            &&
                            (a.getExpireTime() == null ? true : (a.getExpireTime().isAfter(LocalDateTime.now())))
                        )
                )
                .collect(Collectors.toList());
    }

}
