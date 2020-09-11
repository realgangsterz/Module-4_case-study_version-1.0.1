package com.codegym.repository;

import com.codegym.model.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    Optional<User> findByUserName(String userName);
}
