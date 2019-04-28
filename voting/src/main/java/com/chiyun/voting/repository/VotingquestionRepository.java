package com.chiyun.voting.repository;

import com.chiyun.voting.entity.VotingquestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public interface VotingquestionRepository extends JpaRepository<VotingquestionEntity, Integer> {
    @Modifying
    @Transactional
    int deleteAllById(int id);

    List<VotingquestionEntity> findAllByHdid(int hdid);

    @Query(value = "select id,title bt from votingquestion where themeid = ?1 ", nativeQuery = true)
    List<Map> findAllIdByHdid(int hdid);

    @Query(value = "select count(*) from votingquestion,voting where themeid = ?1 and qid = id and pid = ?2", nativeQuery = true)
    int existsBypidAndHdid(int hdid, int pid);
}
