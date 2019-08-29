package com.tsikalov.proj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String extension;

    private Double weight;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "file")
    private List<Comment> comments = new ArrayList<>();
}
