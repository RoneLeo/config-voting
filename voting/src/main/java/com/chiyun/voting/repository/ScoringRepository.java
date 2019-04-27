package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringEntity;
import com.chiyun.voting.entity.ScoringEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringRepository extends JpaRepository<ScoringEntity, ScoringEntityPK> {
}
