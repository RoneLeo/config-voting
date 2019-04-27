package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringquestionoptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoringQORepository extends JpaRepository<ScoringquestionoptionsEntity, Integer> {
    List<ScoringquestionoptionsEntity> findAllByHdid(int hdid);

    @Modifying
    @Transactional
    int deleteAllById(int id);
}
