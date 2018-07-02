package com.sexyalbum.jdbc;

import com.sexyalbum.model.LikeRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LikeRelationDaoImpl implements LikeRelationDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int add(LikeRelation likeRelation) {
        return template.update("insert into like_relation(userid, eleid) values (?, ?)",
                likeRelation.getUserid(), likeRelation.getEleid());
    }

    @Override
    public int delete(LikeRelation likeRelation) {
        return template.update("delete from like_relation where userid=? and eleid=?",
                likeRelation.getUserid(), likeRelation.getEleid());
    }

    @Override
    public int deleteUserRelations(Long userid) {
        return template.update("delete from like_relation where userid=?",
                userid);
    }

    @Override
    public int deleteEleRelations(Long eleid) {
        return template.update("delete from like_relation where eleid=?",
                eleid);
    }

    @Override
    public List<Long> findUserLikes(Long userid) {
        List<Long> elesid=template.query("select * from like_relation where userid=?", new Object[]{userid},
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getLong("eleid");
                    }
                });
        if(elesid!=null&&!elesid.isEmpty())
            return elesid;
        else
            return null;
    }

    @Override
    public List<Long> findEleLikers(Long eleid) {
        List<Long> likersid=template.query("select * from like_relation where eleid=?", new Object[]{eleid},
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getLong("userid");
                    }
                });
        if(likersid!=null&&!likersid.isEmpty())
            return likersid;
        else
            return null;
    }

    @Override
    public List<LikeRelation> findWholeLikeRelationList() {
        List<LikeRelation> likeRelations=template.query("select * from like_relation", new Object[]{},
                new BeanPropertyRowMapper<>(LikeRelation.class));
        if(likeRelations!=null&&!likeRelations.isEmpty())
            return likeRelations;
        else
            return null;
    }
}
