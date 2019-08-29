package com.tsikalov.proj.dto.response;

import com.tsikalov.proj.entity.Comment;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CommentResponse {
    private Long id;

    private String text;

    private LocalDate localDate;

    private LocalTime localTime;

    private Long fileId;

    public CommentResponse(Comment comment) {
        id = comment.getId();
        text = comment.getText();
        localDate = comment.getLocalDate();
        localTime = comment.getLocalTime();
        fileId = comment.getFile().getId();
    }
}
