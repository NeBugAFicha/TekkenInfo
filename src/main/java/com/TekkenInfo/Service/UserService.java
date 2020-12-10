package com.TekkenInfo.Service;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.User;

import java.util.List;

public interface UserService {
    void addChar(Char character);
    List<Char> findAll();
    void deleteChar(String charName);
    Char findByName(String charName);
    void updateChar(Char character,String oldName);
    void addUser(User user);
    User findByUsername(String username);

}
