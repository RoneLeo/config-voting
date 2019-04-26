package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringquestionoptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoringQORepository extends JpaRepository<ScoringquestionoptionsEntity, Integer> {
    List<ScoringquestionoptionsEntity> findAllByHdid(int hdid);
}
