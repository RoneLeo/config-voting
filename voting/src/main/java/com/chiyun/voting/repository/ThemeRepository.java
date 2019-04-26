package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {
    @Modifying
    @Transactional
    int deleteAllById(int id);
}
