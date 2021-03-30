package com.TekkenInfo.Service;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Role;
import com.TekkenInfo.Domain.User;
import com.TekkenInfo.Mapper.CharMapper;

import com.TekkenInfo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import java.sql.ResultSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService{
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public void addChar(Char character){
        String sql = "INSERT into characters (name,style,tier,image) values (?,?,?,?)";
        jdbcTemplate.update(sql,character.getName(),character.getFightingStyle(),character.getTierLvl().toString(),character.getImage());
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

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


}
