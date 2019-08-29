package com.tsikalov.proj.dto.response;

import com.tsikalov.proj.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private Long countFiles;


    public CategoryResponse(Category category) {
        id = category.getId();
        name = category.getName();
        countFiles = category.getCountFiles();
    }
}
