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

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

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
    public String addTag(HttpServletRequest req, Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }

        Tag tag = new Tag();
        tag.setName(map.get("name")[0]);
        tService.addTag(tag);
        model.addAttribute("tags",tService.getAllTags());
        return "/tag.html";
    }

    @PostMapping("/editTag")
    public ResponseEntity editTag(HttpServletRequest req,
                                  @RequestParam("id") int id,
                                  Model model){

        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }

        Tag tag = new Tag();
        tag.setName(map.get("name")[0]);
        tag.setTid(id);
        tService.updateTag(tag);
        return ResponseEntity.ok().body(tag);
    }

    @GetMapping("/deleteTag")
    public void deleteTag(@RequestParam("id") int id){
        tService.deleteTagById(id);
    }


}
