package com.tsikalov.proj.service;

import com.tsikalov.proj.dto.request.TagRequest;
import com.tsikalov.proj.dto.response.TagResponse;
import com.tsikalov.proj.entity.Tag;
import com.tsikalov.proj.exception.NoMatchesException;
import com.tsikalov.proj.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<TagResponse> findAll(String fieldName) {
        return tagRepository.findAll(Sort.by(fieldName)).stream()
                .map(TagResponse::new)
                .collect(Collectors.toList());
    }

    public void save(TagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        tagRepository.save(tag);
    }

    public void update(TagRequest request, Long id) {
        tagRepository.save(tagRequestToTag(findOne(id), request));
    }

    public Tag findOne(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new NoMatchesException("Tag with id " + id + " not exists"));
    }

    private Tag tagRequestToTag(Tag tag,
                                               TagRequest request) {
        if (tag == null) {
            tag = new Tag();
        }
        tag.setName(request.getName());
        return tag;
    }
}
