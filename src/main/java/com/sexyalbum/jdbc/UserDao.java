package com.sexyalbum.jdbc;

import com.sexyalbum.model.User;

import java.util.List;

public interface UserDao {
    int add(User user);
    int update(User user);
    int delete(Long userid);
    User find(Long userid);

    User find(String username);

    //todo
    List<User> findList();

    List<User> findWholeUserList();
}
