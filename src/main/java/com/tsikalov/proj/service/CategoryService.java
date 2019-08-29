package com.tsikalov.proj.service;

import com.tsikalov.proj.dto.request.CategoryRequest;
import com.tsikalov.proj.dto.response.CategoryResponse;
import com.tsikalov.proj.entity.Category;
import com.tsikalov.proj.exception.HasDependenciesException;
import com.tsikalov.proj.exception.NoMatchesException;
import com.tsikalov.proj.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void save(CategoryRequest request) {
        categoryRepository.save(
                categoryRequestToCategory(null, request));
    }

    public List<CategoryResponse> findAll(String fieldName) {
        return categoryRepository.findAll(Sort.by(fieldName)).stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public Category findOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoMatchesException("Category with id " + id + " not exists"));
    }

    public void update(CategoryRequest request, Long id) {
        categoryRepository.save(categoryRequestToCategory(findOne(id), request));
    }

    public void delete(Long id) {
        Category category = findOne(id);
        if (category.getFiles().isEmpty()) {
            categoryRepository.delete(category);
        } else {
            throw new HasDependenciesException("Cannot delete category with id " + id + " because it has dependencies");
        }
    }

    private Category categoryRequestToCategory(Category category,
                                               CategoryRequest request) {
        if (category == null) {
            category = new Category();
        }
        category.setName(request.getName());
        return category;
    }
}
