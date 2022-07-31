/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wiley.contentmanagement.service;

import com.wiley.contentmanagement.dao.TagDao;
import com.wiley.contentmanagement.model.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author erres
 */
@Component
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public Tag getTagById(int tid) {
        return tagDao.getTagById(tid);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagDao.addTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }

    @Override
    public void deleteTagById(int tid) {
        tagDao.deleteTagById(tid);
    }

}
