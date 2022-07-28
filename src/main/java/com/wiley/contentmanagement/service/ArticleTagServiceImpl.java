package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.dao.ArticleTagDao;
import com.wiley.contentmanagement.model.ArticleTag;
import com.wiley.contentmanagement.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleTagServiceImpl implements ArticleTagService{
    @Autowired
    ArticleTagDao atDao;

    @Override
    public List<Tag> getArticleTagByAid(int aid) {
        return atDao.getAllArticleTags()
                .stream()
                .filter(at->at.getArticle().getAid()==aid)
                .map(ArticleTag::getTag)
                .collect(Collectors.toList());
    }
}
