package com.chiyun.voting.repository;

import com.chiyun.voting.entity.VotingEntity;
import com.chiyun.voting.entity.VotingEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotingRepository extends JpaRepository<VotingEntity, VotingEntityPK> {

    List<VotingEntity> findAllByQidAndBzIsNotNull(int qid);
}
