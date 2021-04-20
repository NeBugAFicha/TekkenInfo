package com.TekkenInfo.Repos;

import com.TekkenInfo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
        User findByUsername(String username);
        List<User> findAll();
}