package com.sexyalbum.service;

import com.sexyalbum.model.User;

public interface UserService {
    // 初始化用户数据
    Long createUser(User user);
    // 仅作更新用户名, 密码等基础信息
    int updateUser(User user);
    // 删除用户
    int deleteUser(User user);
    // 获取用户实例
    User getUser(Long userid);
    // 验证用户
    Long verifyUser(User user);
    // todo
}
