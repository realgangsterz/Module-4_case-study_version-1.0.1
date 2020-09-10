package com.codegym.service;

import com.codegym.model.Category;


public interface CategoryService {
    Category findById(Long id);
    Iterable<Category> findAll();

    void save(Category category);

    void remove(Long categoryId);
}
