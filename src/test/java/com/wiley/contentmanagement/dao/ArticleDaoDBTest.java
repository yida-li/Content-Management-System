package com.wiley.contentmanagement.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleDaoDBTest {

    @Autowired
    ArticleDao aDao;

    @Test
    public void getAll() {
//        List<Article> allArticles = aDao.getAllArticles();
//        for (Article article : allArticles) {
//            System.out.println(article);
//        }
    }
}
