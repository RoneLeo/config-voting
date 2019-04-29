package com.chiyun.voting.service;

import com.chiyun.voting.entity.UserEntity;
import com.chiyun.voting.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl {
    @Resource
    private UserRepository userRepository;

    public UserEntity findFirstByZh(String zh) {
        return userRepository.findFirstByZh(zh);
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public boolean existsByZh(String zh) {
        return userRepository.existsByZh(zh);
    }
}
