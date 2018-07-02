package com.sexyalbum.service;

import com.sexyalbum.jdbc.UserDao;
import com.sexyalbum.jdbc.UserRelationDao;
import com.sexyalbum.model.User;
import com.sexyalbum.model.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRelationDao userRelationDao;

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

    @Override
    public User getUser(String username) {
        return userDao.find(username);
    }

    @Override
    public int addFriend(Long userid, Long friendid) {
        return userRelationDao.add(new UserRelation(userid, friendid));
    }

    @Override
    public int deleteFriend(Long userid, Long friendid) {
        return userRelationDao.deleteRelation(new UserRelation(userid, friendid));
    }

    @Override
    public int deleteUserFriends(Long userid) {
        return userRelationDao.deleteUserRelations(userid);
    }

    @Override
    public List<Long> getFollowers(Long userid) {
        return userRelationDao.findUserFollowers(userid);
    }

    @Override
    public List<Long> getFollowings(Long userid) {
        return userRelationDao.findUserFollowings(userid);
    }

    @Override
    public Long verifyUser(User user) {
        User userInfo=null;
        if(user!=null)
            userInfo=userDao.find(user.getUsername());
        if(userInfo!=null)
            if(user.getPassword().equals(userInfo.getPassword()))
                return userInfo.getUserid();
        return null;
    }
}
