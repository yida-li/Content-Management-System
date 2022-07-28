package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.model.Tag;

import java.util.List;

public interface ArticleTagService {
    List<Tag> getArticleTagByAid(int aid);
}
