package com.chiyun.voting.repository;

import com.chiyun.voting.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findFirstByZhAndMm(String zh, String mm);

    boolean existsByZh(String zh);
}
