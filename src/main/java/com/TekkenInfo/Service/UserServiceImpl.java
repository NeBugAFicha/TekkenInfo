package com.TekkenInfo.Service;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Role;
import com.TekkenInfo.Domain.User;
import com.TekkenInfo.Mapper.CharMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addChar(Char character){
        String sql = "INSERT into characters (name,style,tier) values (?,?,?)";
        jdbcTemplate.update(sql,character.getName(),character.getFightingStyle(),character.getTierLvl().toString());
    }
    @Override
    public List<Char> findAll(){
        String sql = "Select * from characters";
        return jdbcTemplate.query(sql,new CharMapper());
    }

    @Override
    public void deleteChar(String charName){
        String sql = "Delete from characters where name = ?";
        jdbcTemplate.update(sql,charName);
    }

    @Override
    public Char findByName(String charName){
        String sql = "Select * from characters where name = ?";
        Char character = jdbcTemplate.query(sql,new CharMapper(),charName).get(0);
        return character;
    }
    @Override
    public void updateChar(Char character, String oldName){
        String sql = "Update characters set name = ?, style = ?, tier = ? where name = ?";
        jdbcTemplate.update(sql,character.getName(),character.getFightingStyle(),character.getTierLvl().toString(),oldName);
    }

    @Override
    public User findByUsername(String username){
        String sql = "Select * from usr where username = ?";
        if(jdbcTemplate.queryForRowSet(sql,username).next()) return new User();
        else return null;
    }

    @Override
    public void addUser(User user){
        jdbcTemplate.update("ALTER TABLE usr AUTO_INCREMENT=1");
        String sql = "INSERT INTO usr (username,password,active) values (?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),passwordEncoder.encode(user.getPassword()),user.isActive());
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet("select id from usr where username=?",user.getUsername());
        if(resultSet.next())
        jdbcTemplate.update("insert into user_role (user_id, roles) values (?,?)",resultSet.getInt(1), Role.USER.toString());
    }


}
