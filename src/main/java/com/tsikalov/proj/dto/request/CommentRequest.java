package com.tsikalov.proj.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CommentRequest {
    @Column(columnDefinition = "text")
    private String text;

    private LocalDate date;

    private LocalTime time;

    private Long userId;

    private Long fileId;
}
