package com.tsikalov.proj.service;


import com.tsikalov.proj.dto.request.FileRequest;
import com.tsikalov.proj.dto.response.FileResponse;
import com.tsikalov.proj.dto.response.PageResponse;
import com.tsikalov.proj.entity.File;
import com.tsikalov.proj.exception.NoMatchesException;
import com.tsikalov.proj.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    public void save(FileRequest request) {
        fileRepository.save(fileRequestToFile(null, request));
    }

    public PageResponse<FileResponse> findPage(Integer page, Integer size, String fieldName, Sort.Direction direction) {
        Page<File> data = fileRepository.findAll(PageRequest.of(page, size, direction, fieldName));
        List<FileResponse> collect = data.get().map(FileResponse::new).collect(Collectors.toList());
        return new PageResponse<>(data.getTotalElements(),
                data.getTotalPages(),
                collect);

    }

    public void update(FileRequest request, Long id) {
        fileRepository.save(fileRequestToFile(findOne(id), request));
    }

    public File findOne(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new NoMatchesException("File with id " + id + " not exists"));
    }

    private File fileRequestToFile(File file, FileRequest request) {
        if (file == null) {
            file = new File();
        }
        file.setName(request.getName());
        file.setExtension(request.getExtension());
        file.setWeight(request.getWeight());
        file.setCategory(categoryService.findOne(request.getCategoryId()));
        file.setTag(tagService.findOne(request.getTagId()));
        file.setUser(userService.findOne(request.getUserId()));
        return file;
    }
}
