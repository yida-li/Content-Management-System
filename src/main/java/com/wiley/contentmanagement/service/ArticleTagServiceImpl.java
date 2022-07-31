package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.dao.ArticleDao;
import com.wiley.contentmanagement.dao.ArticleTagDao;
import com.wiley.contentmanagement.model.Article;
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

    @Autowired
    ArticleDao aDao;

    @Override
    public List<Tag> getArticleTagByAid(int aid) {
        return atDao.getAllArticleTags()
                .stream()
                .filter(at->at.getArticle().getAid()==aid)
                .map(ArticleTag::getTag)
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getArticlesByTid(int tid) {
        return atDao.getAllArticleTags()
                .stream()
                .filter(at->at.getTag().getTid()==tid)
                .map(ArticleTag::getArticle)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticleTagByAid(int aid) {
        List<Integer> atList = atDao.getAllArticleTags()
                .stream()
                .filter(at->at.getArticle().getAid()==aid)
                .map(ArticleTag::getAtid)
                .collect(Collectors.toList());

        for(Integer at:atList){
            atDao.deleteArticleTagById(at);
        }
    }

    @Override
    public void deleteATByAidAndTid(int aid, int tid) {
        List<Integer> atList = atDao.getAllArticleTags()
                .stream()
                .filter(at->(at.getArticle().getAid()==aid&&at.getTag().getTid()==tid))
                .map(ArticleTag::getAtid)
                .collect(Collectors.toList());

        for(Integer at:atList){
            atDao.deleteArticleTagById(at);
        }
    }


    @Override
    public int updateArticleTag(int aid,List<Integer> tid) {
        Article article = aDao.getArticleById(aid);

        // original tag id list
        // for example origin:{1,2,3,5}
        // new:{1,2,4}
        List<Integer> tidList = getArticleTagByAid(aid)
                .stream()
                .map(Tag::getTid)
                .collect(Collectors.toList());

        // if 0, delete all
        if(tid.size()==0){
            deleteArticleTagByAid(aid);
        }else{
            for(Integer id : tidList){
                if(tid.contains(id)){
                    // skip{1,2}
                    tid.remove(tid.indexOf(id));
                }else{
                    // delete{3,5}
                    deleteATByAidAndTid(aid,id);
                }
            }
        }

        // {4} remains
        if(tid.size() != 0){
            for(Integer id : tid){
                ArticleTag articleTag = new ArticleTag();
                Tag tempTag = new Tag();
                tempTag.setTid(id);
                Article tempA = new Article();
                tempA.setAid(aid);
                articleTag.setTag(tempTag);
                articleTag.setArticle(tempA);
                atDao.addArtricleTag(articleTag);
            }

        }

        return aid;
    }
}
