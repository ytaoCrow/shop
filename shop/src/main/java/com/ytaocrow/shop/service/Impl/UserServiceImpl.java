package com.ytaocrow.shop.service.Impl;

import com.ytaocrow.shop.dao.UserDao;
import com.ytaocrow.shop.dto.UserLoginRequest;
import com.ytaocrow.shop.dto.UserRegisterRequest;
import com.ytaocrow.shop.model.User;
import com.ytaocrow.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private  final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊的 email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null){
            log.warn("該 email {} 已經被註冊了", userRegisterRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // MD5 Hash

        String hashedPassword =
                DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);
        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //check user
        if(user == null){
            log.warn("該email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //MD5 HashedPassword
        String hashedPassword =
                DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //比較密碼
        if (user.getPassword().equals(hashedPassword)) {
            return  user;
        }else {

            log.warn("該email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
    }
}
