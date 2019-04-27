package com.chiyun.voting.repository;

import com.chiyun.voting.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {
    @Modifying
    @Transactional
    int deleteAllById(int id);

    @Modifying
    @Transactional
    @Query(value = "update theme set publish =?2 where id=?1", nativeQuery = true)
    int updateFbzt(int id, int fbzt);

    @Query(value = "select * from theme where id = (select themeid from votingquestion where votingquestion.id=?1)", nativeQuery = true)
    ThemeEntity findAllByVoteId(int vid);

    @Query(value = "select * from theme where id = (select themeid from scoringquestion where scoringquestion.id=?1)", nativeQuery = true)
    ThemeEntity findAllByScoreId(int vid);


}
