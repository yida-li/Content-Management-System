package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.TestApplicationConfiguration;
import com.wiley.contentmanagement.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class ArticleDaoDBTest {
    @Autowired
    ArticleDao aDao;

    @Test
    public void getAll() {
        List<Article> allArticles = aDao.getAllArticles();
        for(Article article : allArticles){
            System.out.println(article);
        }
    }
}