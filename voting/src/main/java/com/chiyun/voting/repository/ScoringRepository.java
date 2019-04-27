package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringEntity;
import com.chiyun.voting.entity.ScoringEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ScoringRepository extends JpaRepository<ScoringEntity, ScoringEntityPK> {

    @Query(value = "select id,themeid hdid,scorelow zdf,scorehign zgf,scorename mc ,scoredesc mcms,score df from scoringquestionoptions,scoring where pid = ?1 and oid = id and qid = ?2", nativeQuery = true)
    List<Map> findAllByPidAndQid(int pid, int qid);
}
