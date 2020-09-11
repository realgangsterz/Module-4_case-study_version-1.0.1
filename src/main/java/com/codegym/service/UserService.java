package com.codegym.service;

import com.codegym.model.User;

public interface UserService {
    User findById(Long id);

    User findByUserName(String userName);

    Iterable<User> findAll();

    User save(User user);

    User remove(Long userId);

}
