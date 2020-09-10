package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Producer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProducerRepository extends PagingAndSortingRepository<Producer, Long> {
}

