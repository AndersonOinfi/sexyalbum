package com.sexyalbum.jdbc;

import com.sexyalbum.model.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRelationDaoImpl implements UserRelationDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int add(UserRelation relation) {
        return template.update("insert into user_relation(userid, friendid) values (?, ?)",
                relation.getUserid(), relation.getFriendid());
    }

    @Override
    public int deleteRelation(UserRelation relation) {
        return template.update("delete from user_relation where userid=? and friendid=?",
                relation.getUserid(), relation.getFriendid());
    }

    @Override
    public int deleteUserRelations(Long userid) {
        return template.update("delete from user_rlation where userid=?",
                userid);
    }

    @Override
    public List<Long> findUserFollowers(Long userid) {
        List<Long> followersid=template.query("select * from user_relation where friendid=?", new Object[]{userid},
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getLong("userid");
                    }
                });
        if(followersid!=null&&!followersid.isEmpty())
            return followersid;
        else
            return null;
    }

    @Override
    public List<Long> findUserFollowings(Long userid) {
        List<Long> followingsid=template.query("select * from user_relation where userid=?", new Object[]{userid},
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getLong("friendid");
                    }
                });
        if(followingsid!=null&&!followingsid.isEmpty())
            return followingsid;
        else
            return null;
    }

    @Override
    public List<UserRelation> findWholeUserRelationList() {
        List<UserRelation> userRelations=template.query("select * from user_relation", new Object[]{},
                new BeanPropertyRowMapper<>(UserRelation.class));
        if(userRelations!=null&&!userRelations.isEmpty())
            return userRelations;
        else
            return null;
    }
}
