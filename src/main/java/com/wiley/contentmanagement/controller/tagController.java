package com.wiley.contentmanagement.controller;

import com.wiley.contentmanagement.model.Tag;
import com.wiley.contentmanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class tagController {
    @Autowired
    TagService tService;

    @GetMapping("/getAllTags")
    public ResponseEntity getAllTags(Model model){
        model.addAttribute("tags",tService.getAllTags());
        return ResponseEntity.ok().body(tService.getAllTags());
    }

    @PostMapping("/addTag")
    public void addTag(@RequestParam("name") String name,Model model){
        Tag tag = new Tag();
        tag.setName(name);
        tService.addTag(tag);
        model.addAttribute("tags",tService.getAllTags());
    }

    @GetMapping("/editTag")
    public ResponseEntity editTag(@RequestParam("id") int id,
                                  @RequestParam("name") String name,
                                  Model model){
        Tag tag = new Tag();
        tag.setName(name);
        tag.setTid(id);
        tService.updateTag(tag);
        return ResponseEntity.ok().body(tag);
    }



}
