package com.ytaocrow.shop.service.Impl;

import com.ytaocrow.shop.dao.UserDao;
import com.ytaocrow.shop.dto.UserRegisterRequest;
import com.ytaocrow.shop.model.User;
import com.ytaocrow.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
