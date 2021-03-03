package com.woniuxy.mapper;

import com.woniuxy.model.User;

public interface UserMapper {
    User findByUser(String username);
}
