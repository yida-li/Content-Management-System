/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Tag;
import java.util.List;

/**
 *
 * @author erres
 */
public interface TagDao {

    Tag getTagById(int tid);

    List<Tag> getAllTags();

    Tag addTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTagById(int tid);
}
