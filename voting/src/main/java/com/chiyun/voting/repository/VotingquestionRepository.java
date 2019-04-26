package com.chiyun.voting.repository;

import com.chiyun.voting.entity.VotingquestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface VotingquestionRepository extends JpaRepository<VotingquestionEntity, Integer> {
    @Modifying
    @Transactional
    int deleteAllById(int id);

    List<VotingquestionEntity> findAllByHdid(int hdid);
}
