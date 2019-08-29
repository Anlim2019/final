package com.tsikalov.proj.service;

import com.tsikalov.proj.dto.request.CommentRequest;
import com.tsikalov.proj.dto.response.CommentResponse;
import com.tsikalov.proj.entity.Comment;
import com.tsikalov.proj.exception.HasDependenciesException;
import com.tsikalov.proj.exception.NoMatchesException;
import com.tsikalov.proj.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FileService fileService;

    public void save(CommentRequest request) {
        commentRepository.save(
                commentRequestToComment(null, request));
    }

    public List<CommentResponse> findAll(String fieldName) {
        return commentRepository.findAll(Sort.by(fieldName)).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    public Comment findOne(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoMatchesException("Comment with id " + id + " not exists"));
    }

    public void update(CommentRequest request, Long id) {
        commentRepository.save(commentRequestToComment(findOne(id), request));
    }
    public void delete(Long id) {
        Comment comment = findOne(id);
        if (comment != null) {
            commentRepository.delete(comment);
        } else {
            throw new NoMatchesException("Comment with id " + id + " not exists");
        }
    }

    private Comment commentRequestToComment(Comment comment,
                                            CommentRequest request) {
        if (comment == null) {
            comment = new Comment();
        }
        comment.setText(request.getText());
        comment.setLocalDate(request.getDate());
        comment.setLocalTime(request.getTime());
        comment.setFile(fileService.findOne(request.getFileId()));
        return comment;
    }
}
