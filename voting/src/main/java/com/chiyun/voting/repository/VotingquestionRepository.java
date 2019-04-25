package com.chiyun.voting.repository;

import com.chiyun.voting.entity.VotingquestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingquestionRepository extends JpaRepository<VotingquestionEntity, Integer> {
}
