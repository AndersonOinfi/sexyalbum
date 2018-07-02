package com.sexyalbum.jdbc;

import com.sexyalbum.model.Comment;

public interface CommentDao {
    int add(Comment comment);

    int delete(Comment comment);
}
