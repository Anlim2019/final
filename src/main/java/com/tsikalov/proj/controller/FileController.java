package com.tsikalov.proj.controller;


import com.tsikalov.proj.dto.request.FileRequest;
import com.tsikalov.proj.dto.response.FileResponse;
import com.tsikalov.proj.dto.response.PageResponse;
import com.tsikalov.proj.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    public void save(@Valid @RequestBody FileRequest request) {
        fileService.save(request);
    }

    @GetMapping
    public PageResponse<FileResponse> findPage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(defaultValue = "name") String fieldName,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        return fileService.findPage(page, size, fieldName, direction);
    }

    @PutMapping
    public void update(@Valid @RequestBody FileRequest request, Long id) {
        fileService.update(request, id);
    }
}
