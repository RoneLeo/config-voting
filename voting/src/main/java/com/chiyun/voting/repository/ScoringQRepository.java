package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringquestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoringQRepository extends JpaRepository<ScoringquestionEntity, Integer> {
    List<ScoringquestionEntity> findAllByHdid(int hdid);

    @Modifying
    @Transactional
    int deleteAllById(int id);

}
