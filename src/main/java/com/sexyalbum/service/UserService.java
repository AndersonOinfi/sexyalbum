package com.sexyalbum.service;

import com.sexyalbum.model.User;

public interface UserService {
    // 初始化用户数据
    int createUser();
    // 仅作更新用户名, 密码等基础信息
    int updateUser();
    // 删除用户
    int deleteUser();
    // 获取用户实例
    User getUser();

    // todo
}
