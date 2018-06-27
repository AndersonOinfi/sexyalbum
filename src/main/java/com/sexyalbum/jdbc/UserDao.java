package com.sexyalbum.jdbc;

import com.sexyalbum.model.User;

import java.util.List;

public interface UserDao {
    int add(User user);
    int update(User user);
    int delete(Long id);
    User find(Long id);
    //todo
    List<User> findList();

    List<User> findWholeUserList();
}
