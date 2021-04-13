package com.TekkenInfo.Mapper;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Tier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class CharMapper implements RowMapper<Char> {

    @Override
    public Char mapRow(ResultSet resultSet, int i) throws SQLException {
        Char character = new Char();
        character.setName(resultSet.getString("name"));
        character.setFightingStyle(resultSet.getString("style"));
        character.setTierLvl(Tier.valueOf(resultSet.getString("tier")));
        character.setImage(resultSet.getString("image"));
        return character;
    }
}