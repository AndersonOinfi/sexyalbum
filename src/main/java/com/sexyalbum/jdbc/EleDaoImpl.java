package com.sexyalbum.jdbc;

import com.sexyalbum.model.AlbumElementable;
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
    public int add(AlbumElementable ele) {
        return template.update("insert into ele(eleid, source, description) values(?, ?, ?)",
                ele.getEleId(), ele.getSource(), ele.getDescription());
    }

    // 相册元素暂不提供修改功能
    @Override
    public int update(AlbumElementable ele) {
        return 0;
    }

    // 不允许直接使用此方法删除ele
    @Override
    public int delete(Long id) {
        return template.update("delete * from ele where eleid=?", id);
    }

    @Override
    public String find(Long id) {
        List<String> ele=template.query("select * from ele where eleid=?", new Object[]{id},
                new EleRowMapper());
        if(ele!=null&&!ele.isEmpty())
            return ele.get(0);
        else
            return null;
    }

    @Override
    public List<String> findWholeEleList() {
        List<String> eles=template.query("select * from ele", new Object[]{},
                new EleRowMapper());
        if(eles!=null&&!eles.isEmpty())
            return eles;
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
