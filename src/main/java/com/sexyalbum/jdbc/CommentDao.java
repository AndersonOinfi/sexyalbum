package com.sexyalbum.jdbc;

import com.sexyalbum.model.Comment;

import java.util.List;

public interface CommentDao {
    Long add(Comment comment);

    int delete(Comment comment);

    List<Comment> findUserComments(Long userid);

    List<Comment> findEleComments(Long eleid);
}
