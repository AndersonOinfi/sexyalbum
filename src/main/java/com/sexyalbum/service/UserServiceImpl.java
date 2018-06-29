package com.sexyalbum.service;

import com.sexyalbum.jdbc.UserDao;
import com.sexyalbum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Long createUser(User user) {
        return userDao.add(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public int deleteUser(User user) {
        return userDao.delete(user.getUserid());
    }

    @Override
    public User getUser(Long userid) {
        return userDao.find(userid);
    }
}
