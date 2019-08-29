package com.tsikalov.proj.dto.response;

import com.tsikalov.proj.entity.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagResponse {
    private Long id;

    private String name;

    public TagResponse(Tag tag) {
        id = tag.getId();

        name = tag.getName();
    }
}
