package com.ytaocrow.shop.dao;

import com.ytaocrow.shop.dto.UserRegisterRequest;
import com.ytaocrow.shop.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
