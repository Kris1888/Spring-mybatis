package com.woniuxy.service;

import com.woniuxy.model.User;

public interface UserService {
    User findByUser(String username);
}
