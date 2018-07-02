package com.sexyalbum.jdbc;

import com.sexyalbum.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int add(Comment comment) {
        return template.update("insert into comment(eleid, userid, tarid, comments) values (?, ?, ?, ?)",
                comment.getEleid(), comment.getUserid(), comment.getTarid(), comment.getComments());
    }

    @Override
    public int delete(Comment comment) {
        return 0;
    }
}
