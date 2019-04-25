package com.chiyun.voting.service;

import com.chiyun.voting.entity.VotingquestionEntity;
import com.chiyun.voting.entity.VotingquestionoptionsEntity;
import com.chiyun.voting.repository.VotingquestionRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class VotingServiceImpl {
    private final VotingquestionRepository votingquestionRepository;
    private final JdbcTemplate jdbcTemplate;

    public VotingServiceImpl(VotingquestionRepository votingquestionRepository, JdbcTemplate jdbcTemplate) {
        this.votingquestionRepository = votingquestionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public boolean saveVoting(VotingquestionEntity votingquestionEntity, @RequestBody List<VotingquestionoptionsEntity> list, boolean qt) {
        VotingquestionEntity entity = votingquestionRepository.save(votingquestionEntity);
        if (entity == null) {
            return false;
        }
        if (qt) {
            VotingquestionoptionsEntity votingquestionoptionsEntity = new VotingquestionoptionsEntity();
            votingquestionoptionsEntity.setXxmc("其他");
            votingquestionoptionsEntity.setXxlx(4);
            list.add(votingquestionoptionsEntity);
        }
        for (VotingquestionoptionsEntity ve : list) {
            ve.setDxid(entity.getId());
        }
        String sql = "insert into votingquestionoptions(qid,name,content,otype) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getDxid());
                ps.setString(2, list.get(i).getXxmc());
                ps.setString(3, list.get(i).getXxnr());
                ps.setInt(4, list.get(i).getXxlx());
            }

            public int getBatchSize() {
                return list.size();
            }
        });
        return true;
    }
}
