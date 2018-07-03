package com.sexyalbum.service;

import com.sexyalbum.model.Message;
import com.sexyalbum.model.User;

import java.util.List;

public interface UserService {
    // 初始化用户数据
    Long createUser(User user);
    // 仅作更新用户名, 密码等基础信息
    int updateUser(User user);
    // 删除用户
    int deleteUser(User user);
    // 获取用户实例
    User getUser(Long userid);
    User getUser(String userid);

    int addFriend(Long userid, Long friendid);

    int deleteFriend(Long userid, Long friendid);

    int deleteUserFriends(Long userid);

    List<Long> getFollowers(Long userid);

    List<Long> getFollowings(Long userid);

    // like service
    int likeEle(Long userid, Long eleid);

    int cancelLike(Long userid, Long eleid);

    List<Long> getUserLikes(Long userid);

    List<Long> getEleLikers(Long eleid);

    // 验证用户
    Long verifyUser(User user);

    Long createMessage(Message message);

    List<Message> getUserMessages(Long userid);

    List<Message> getUserUnreadMessages(Long userid);

    List<Message> getUserMainMessages(Long userid);
}
