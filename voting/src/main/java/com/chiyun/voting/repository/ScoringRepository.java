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

    @Query(value = "select name,qid,sum(df) df from (select name,qid,oid,df/sl df from(select name, qid,oid,sum(score) df,count(*) sl from scoring,scoringquestion where qid = id and themeid = ?1 group by qid,oid)a)b group by qid order by df desc ", nativeQuery = true)
    List<Map> getResult(int hdid);

    @Query(value = "select mc, oid, df/ sl df from(select scorename mc,oid,sum(score) df ,count(*) sl from scoring ,scoringquestionoptions where qid = ?1  and oid = id group by oid)b", nativeQuery = true)
    List<Map> getResultByQid(int qid);
}
