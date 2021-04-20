package com.TekkenInfo.Repos;

import com.TekkenInfo.Domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepoTest {
    @Autowired
    UserRepo userRepo;

    @Test
    void findByUsername() {
        System.out.println();
    }
}