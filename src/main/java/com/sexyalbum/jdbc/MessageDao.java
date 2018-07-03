package com.sexyalbum.jdbc;

import com.sexyalbum.model.Message;

import java.util.List;

public interface MessageDao {
    Long add(Message message);

    int update(Long messageid);

    List<Message> findUserMessages(Long userid);
}
