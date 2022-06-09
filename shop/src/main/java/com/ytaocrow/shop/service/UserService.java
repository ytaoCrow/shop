package com.ytaocrow.shop.service;

import com.ytaocrow.shop.dto.UserRegisterRequest;
import com.ytaocrow.shop.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
