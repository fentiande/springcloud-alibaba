package com.chow.service.impl;

import com.chow.dao.UserDao;
import com.chow.domain.User;
import com.chow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByPid(Integer uid) {
        return userDao.findById(uid).get();
    }
}
