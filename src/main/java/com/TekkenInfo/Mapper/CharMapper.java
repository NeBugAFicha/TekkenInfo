package com.TekkenInfo.Mapper;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Tier;
import com.TekkenInfo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CharMapper implements RowMapper<Char> {
    @Autowired
    UserRepo userRepo;
    @Override
    public Char mapRow(ResultSet resultSet, int i) throws SQLException, UsernameNotFoundException {
        Char character = new Char();
        character.setName(resultSet.getString("name"));
        character.setFightingStyle(resultSet.getString("style"));
        character.setTierLvl(Tier.valueOf(resultSet.getString("tier")));
        character.setImage(resultSet.getString("image"));
        character.setCharMakerName(resultSet.getString("charMakerName"));

        return character;
    }
}