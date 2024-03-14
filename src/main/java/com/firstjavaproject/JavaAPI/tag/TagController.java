package com.firstjavaproject.JavaAPI.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagEntity> getTags() {
        return this.tagService.getTags();
    }

    @PostMapping
    public TagEntity addNewTag(@RequestBody TagEntity newTag) {
        return this.tagService.addNewTag(newTag);
    }
}
