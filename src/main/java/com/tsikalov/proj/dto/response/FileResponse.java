package com.tsikalov.proj.dto.response;


import com.tsikalov.proj.entity.File;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponse {
    private Long id;

    private String name;

    private String extension;

    private Double weight;

    private Long tagId;

    private Long userId;

    private Long categoryId;

    public FileResponse(File file) {
        id = file.getId();
        name = file.getName();
        extension = file.getExtension();
        weight = file.getWeight();
        tagId = file.getTag().getId();
        userId = file.getUser().getId();
        categoryId = file.getCategory().getId();
    }
}
