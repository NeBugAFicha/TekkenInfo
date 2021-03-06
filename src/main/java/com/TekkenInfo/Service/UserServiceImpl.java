package com.TekkenInfo.Service;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.User;
import com.TekkenInfo.Mapper.CharMapper;
import com.TekkenInfo.Repos.UserRepo;
import org.apache.logging.log4j.util.Chars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class UserServiceImpl implements UserDetailsService, UserService{
    @Autowired
    public JdbcTemplate jdbcTemplate;
    public static String sortWish = "None";
    @Autowired
    private UserRepo userRepo;

    @Override
    public void addChar(Char character){
        String sql = "INSERT into characters (name,style,tier,image, charMakerName) values (?,?,?,?,?)";
        jdbcTemplate.update(sql,character.getName(),character.getFightingStyle(),character.getTierLvl().toString(),character.getImage(), character.getCharMakerName());
    }
    @Override
    public List<Char> findAll(){
        String sql = "Select * from characters";
        return jdbcTemplate.query(sql, new CharMapper());
    }

    @Override
    public void deleteChar(String charName){
        String sql = "Delete from characters where name = ?";
        jdbcTemplate.update(sql,charName);
    }

    @Override
    public Char findByName(String charName){
        String sql = "Select * from characters where name = '" + charName + "'";
        return jdbcTemplate.queryForObject(sql,new CharMapper());
    }
    @Override
    public void updateChar(Char character, String oldName){
        String sql = "Update characters set name = ?, style = ?, tier = ? where name = ?";
        jdbcTemplate.update(sql,character.getName(),character.getFightingStyle(),character.getTierLvl().toString(),oldName);
    }

    @Override
    public void updateCharMakerNameForChars(String oldName, String newName) {
        String sql = "Update characters set charMakerName = ? where charMakerName = ?";
        jdbcTemplate.update(sql,newName,oldName);
    }

    @Override
    public List<Char> sortChars(String critery) {
        if(critery.equals("Имя(возр.)")) return jdbcTemplate.query("Select * from characters order by name", new CharMapper());
        if(critery.equals("Имя(убыв.)")) return jdbcTemplate.query("Select * from characters order by name DESC", new CharMapper());
        if(critery.equals("Стиль(возр.)")) return jdbcTemplate.query("Select * from characters order by style", new CharMapper());
        if(critery.equals("Стиль(убыв.)")) return jdbcTemplate.query("Select * from characters order by style DESC", new CharMapper());
        if(critery.equals("Тирность(возр.)")) return jdbcTemplate.query("Select * from characters order by tier", new CharMapper());
        if(critery.equals("Тирность(убыв.)")) return jdbcTemplate.query("Select * from characters order by tier DESC ", new CharMapper());
        return null;
    }

    @Override
    public List<Char> filteredChars(String filter) {
        List<Char> allcharsFiltered = findAll();
        if(filter.equals("name")) allcharsFiltered = allcharsFiltered.stream().filter(x-> x.getName().equals(filter)).collect(Collectors.toList());
        if(filter.equals("style")) allcharsFiltered = allcharsFiltered.stream().filter(x-> x.getFightingStyle().equals(filter)).collect(Collectors.toList());
        if(filter.equals("tier")) allcharsFiltered = allcharsFiltered.stream().filter(x-> x.getTierLvl().toString().equals(filter)).collect(Collectors.toList());
        if(filter.equals("author")) allcharsFiltered = allcharsFiltered.stream().filter(x-> x.getCharMakerName().equals(filter)).collect(Collectors.toList());
        return allcharsFiltered;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


}
