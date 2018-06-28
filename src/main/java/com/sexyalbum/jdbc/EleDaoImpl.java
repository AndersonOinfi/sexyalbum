package com.sexyalbum.jdbc;

import com.sexyalbum.model.Ele;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EleDaoImpl implements EleDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int add(Ele ele) {
        return template.update("insert into ele(eleid, source, description) values(?, ?, ?)",
                ele.getEleid(), ele.getSource(), ele.getDescription());
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
