package com.chiyun.voting.repository;

import com.chiyun.voting.entity.VotingEntity;
import com.chiyun.voting.entity.VotingEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface VotingRepository extends JpaRepository<VotingEntity, VotingEntityPK> {

    List<VotingEntity> findAllByQidAndBzIsNotNull(int qid);

    @Query(value = "select * from (select id,qid dxid,name xxmc,content xxnr,otype xxlx,case when sl is null then 0 else sl end  sl from (select * from votingquestionoptions where qid = ?1)a left join (select oid,count(*) sl from voting,votingquestionoptions where oid = id and votingquestionoptions.qid=?1 and voting.qid = votingquestionoptions.qid group by oid)b on oid = id)c order by sl desc ", nativeQuery = true)
    List<Map> getResultByQid(int qid);
}
