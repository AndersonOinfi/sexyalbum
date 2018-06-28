package com.sexyalbum.jdbc;

import com.sexyalbum.model.User;

import java.util.List;

public interface UserDao {
    int add(User user);
    int update(User user);
    int delete(User user);
    User find(Long id);

    User find(String username);

    //todo
    List<User> findList();

    List<User> findWholeUserList();
}
