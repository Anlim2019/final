package com.tsikalov.proj.controller;


import com.tsikalov.proj.dto.request.TagRequest;
import com.tsikalov.proj.dto.response.TagResponse;
import com.tsikalov.proj.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public void save(@Valid @RequestBody TagRequest request) {
        tagService.save(request);
    }

    @GetMapping
    public List<TagResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return tagService.findAll(fieldName);
    }

    @PutMapping
    public void update(@Valid @RequestBody TagRequest request, Long id) {
        tagService.update(request, id);
    }
}
