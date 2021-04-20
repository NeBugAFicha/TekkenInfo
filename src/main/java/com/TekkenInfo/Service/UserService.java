package com.TekkenInfo.Service;

import com.TekkenInfo.Domain.Char;

import java.util.List;

public interface UserService {
    void addChar(Char character);
    List<Char> findAll();
    void deleteChar(String charName);
    Char findByName(String charName);
    void updateChar(Char character,String oldName);
    List<Char> sortChars(String critery);
    void updateCharMakerNameForChars(String oldName, String newName);
}
