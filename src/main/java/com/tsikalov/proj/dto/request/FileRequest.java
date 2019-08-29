package com.tsikalov.proj.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequest {
    private String name;

    private String extension;

    private Double weight;

    private Long userId;

    private Long tagId;

    private Long categoryId;
}
