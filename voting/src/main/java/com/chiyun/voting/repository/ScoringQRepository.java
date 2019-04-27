package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ScoringquestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ScoringQRepository extends JpaRepository<ScoringquestionEntity, Integer> {
    @Query(value = "select id,themeid hdid,name dx ,content nr, exists(select 1 from scoring where qid = id and pid = ?2) zt from scoringquestion where themeid=?1", nativeQuery = true)
    List<Map> findAllByHdid(int hdid, int uid);

    List<ScoringquestionEntity> findAllByHdid(int hdid);

    @Query(value = "select count(*) from scoringquestion,scoring where id = ?1 and qid = id and pid = ?2", nativeQuery = true)
    int existsBypidAndHdid(int dxid, int pid);

    @Modifying
    @Transactional
    int deleteAllById(int id);

}
