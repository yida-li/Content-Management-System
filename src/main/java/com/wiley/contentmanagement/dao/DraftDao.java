package com.wiley.contentmanagement.dao;

import com.wiley.contentmanagement.model.Draft;

import java.util.List;

public interface DraftDao {
    Draft getDraftById(int did);

    List<Draft> getAllDrafts();

    Draft addDraft(Draft draft);

    void updateDraft(Draft draft);

    void deleteDraftById(int did);
}
