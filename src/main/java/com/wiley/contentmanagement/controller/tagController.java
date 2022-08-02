package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Tag;
import com.wiley.contentmanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class tagController {

    @Autowired
    TagService tService;

    @GetMapping("getAllTags")
    public ResponseEntity getAllTags(Model model) {
        model.addAttribute("tags", tService.getAllTags());
        return ResponseEntity.ok().body(tService.getAllTags());
    }

    @PostMapping("addTag")
    public String addTag(String name, Model model) {

        Tag tag = new Tag();
        tag.setName(name);
        tService.addTag(tag);
        model.addAttribute("tags", tService.getAllTags());
        return "redirect:tag";
    }

    @GetMapping("editTag")
    public String editTag(int tid, Model model) {
        model.addAttribute("tag", tService.getTagById(tid));
        return "editTag";
    }

    @PostMapping("editTag")
    public String performEditTag(Tag tag) {
        tService.updateTag(tag);
        return "redirect:tag";
    }

    @GetMapping("deleteTag")
    public String deleteTag(int tid) {
        tService.deleteTagById(tid);
        return "redirect:tag";
    }

}
