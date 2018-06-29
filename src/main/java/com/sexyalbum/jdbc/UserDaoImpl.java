package com.sexyalbum.jdbc;

import com.sexyalbum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Long add(User user) {
        if(find(user.getUsername())!=null)
            return null;
        else {
            template.update("insert into album_user(userid, username, password) values(?, ?, ?)",
                    user.getUserid(), user.getUsername(), user.getPassword());
            return find(user.getUsername()).getUserid();
        }
    }

    @Override
    public int update(User user) {
        return template.update("update album_user set username=?, password=?, where userid=?",
                new Object[]{user.getUsername(), user.getPassword(), 1});
    }

    @Override
    public int delete(Long userid) {
        return template.update("delete from album_user where userid=?", userid);
    }

    //todo
    @Override
    public User find(Long id) {
        List<User> user=template.query("select * from album_user where userid=?",new Object[]{id},
                new BeanPropertyRowMapper(User.class));
        if(user!=null&&!user.isEmpty())
            return user.get(0);
        else
            return null;
    }
    @Override
    public User find(String username){
        List<User> user=template.query("select * from album_user where username=?",new Object[]{username},
                new BeanPropertyRowMapper(User.class));
        if(user!=null&&!user.isEmpty())
            return user.get(0);
        else
            return null;
    }

    @Override
    public List<User> findList() {
        //todo
        return null;
    }

    @Override
    public List<User> findWholeUserList() {
        List<User> users=template.query("select * from album_user",new Object[]{},
                new BeanPropertyRowMapper<User>(User.class));
        if(users!=null&&!users.isEmpty())
            return users;
        else
            return null;
    }
}
