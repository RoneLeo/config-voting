package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringquestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoringQRepository extends JpaRepository<ScoringquestionEntity, Integer> {
    List<ScoringquestionEntity> findAllByHdid(int hdid);
}
