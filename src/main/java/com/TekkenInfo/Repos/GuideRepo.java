package com.TekkenInfo.Repos;

import com.TekkenInfo.Domain.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepo extends JpaRepository<Guide, Long> {
    List<Guide> findAll();
}
