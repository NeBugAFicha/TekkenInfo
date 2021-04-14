package com.TekkenInfo.Repos;

import com.TekkenInfo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
        User findByUsername(String username);
}