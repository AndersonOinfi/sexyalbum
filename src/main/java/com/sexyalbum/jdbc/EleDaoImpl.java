package com.sexyalbum.jdbc;

import com.sexyalbum.model.Ele;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EleDaoImpl implements EleDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Long add(Ele ele) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into ele(source, description) values(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,ele.getSource());
                ps.setString(2,ele.getDescription());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    // 相册元素暂不提供修改功能
    @Override
    public int update(Ele ele) {
        return 0;
    }

    // 不允许直接使用此方法删除ele
    @Override
    public int delete(Long id) {
        return template.update("delete from ele where eleid=?", id);
    }

    @Override
    public Ele find(Long id) {
        List<Ele> ele=template.query("select * from ele where eleid=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Ele.class));
        if(ele!=null&&!ele.isEmpty())
            return ele.get(0);
        else
            return null;
    }

    @Override
    public List<Ele> findWholeEleList() {
        List<Ele> ele=template.query("select * from ele where eleid=?", new Object[]{},
                new BeanPropertyRowMapper<>(Ele.class));
        if(ele!=null&&!ele.isEmpty())
            return ele;
        else
            return null;
    }
}

class EleRowMapper implements RowMapper<String>{

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
