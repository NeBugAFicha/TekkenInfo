package com.TekkenInfo.Repos;

import com.TekkenInfo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
        User findByUsername(String username);
}