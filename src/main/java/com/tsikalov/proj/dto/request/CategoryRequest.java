package com.tsikalov.proj.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private String name;

    private Long countFiles;
}
