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
    public int add(User user) {
        if(find(user.getUsername())!=null)
            return -1;
        else
            return  template.update("insert into a_user(id, username, password) values(?, ?, ?)",
                user.getUserid(), user.getUsername(), user.getPassword());
    }

    @Override
    public int update(User user) {
        return template.update("update a_user set username=?, password=?, where id=?",
                user.getUsername(), user.getPassword(), user.getUserid());
    }

    @Override
    public int delete(User user) {
        return template.update("delete from a_user where id=?", user.getUserid());
    }

    //todo
    @Override
    public User find(Long id) {
        List<User> user=template.query("select * from a_user where id=?",new Object[]{id},
                new BeanPropertyRowMapper(User.class));
        if(user!=null&&!user.isEmpty())
            return user.get(0);
        else
            return null;
    }
    public User find(String username){
        List<User> user=template.query("select * from a_user where username=?",new Object[]{username},
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
        List<User> users=template.query("select * from a_user",new Object[]{},
                new BeanPropertyRowMapper<User>(User.class));
        if(users!=null&&!users.isEmpty())
            return users;
        else
            return null;
    }
}
