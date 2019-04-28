package com.chiyun.voting.service;

import com.chiyun.voting.entity.VotingEntity;
import com.chiyun.voting.entity.VotingquestionEntity;
import com.chiyun.voting.entity.VotingquestionoptionsEntity;
import com.chiyun.voting.repository.VotingQORepository;
import com.chiyun.voting.repository.VotingRepository;
import com.chiyun.voting.repository.VotingquestionRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class VotingServiceImpl {
    private final VotingquestionRepository votingquestionRepository;
    private final VotingQORepository votingQORepository;
    private final JdbcTemplate jdbcTemplate;
    private final VotingRepository votingRepository;

    public VotingServiceImpl(VotingquestionRepository votingquestionRepository, VotingQORepository votingQORepository, JdbcTemplate jdbcTemplate, VotingRepository votingRepository) {
        this.votingquestionRepository = votingquestionRepository;
        this.votingQORepository = votingQORepository;
        this.jdbcTemplate = jdbcTemplate;
        this.votingRepository = votingRepository;
    }

    @Transactional
    public boolean saveVoting(VotingquestionEntity votingquestionEntity, @RequestBody List<VotingquestionoptionsEntity> list, boolean qt) {
        VotingquestionEntity entity = save(votingquestionEntity);
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

    public boolean existById(int id) {
        return votingquestionRepository.existsById(id);
    }

    public VotingquestionEntity findById(int id) {
        Optional<VotingquestionEntity> optional = votingquestionRepository.findById(id);
        return optional.orElse(null);
    }

    public int deleteById(int id) {
        return votingquestionRepository.deleteAllById(id);
    }

    public VotingquestionEntity save(VotingquestionEntity votingquestionEntity) {

        return votingquestionRepository.save(votingquestionEntity);
    }

    public List<VotingquestionEntity> findAllByHdid(int hdid) {
        return votingquestionRepository.findAllByHdid(hdid);
    }

    public VotingquestionoptionsEntity saveVQO(VotingquestionoptionsEntity entity) {
        return votingQORepository.save(entity);
    }

    public int deleteVQO(int id) {
        return votingQORepository.deleteAllById(id);
    }

    @Transactional
    public void vote(List<VotingEntity> list) {
        String sql = "insert into voting(pid,qid,oid,remark) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getPid());
                ps.setInt(2, list.get(i).getQid());
                ps.setInt(3, list.get(i).getOid());
                ps.setString(4, list.get(i).getBz());
            }

            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public int hasVote(int hdid, int uid) {
        return votingquestionRepository.existsBypidAndHdid(hdid, uid);
    }

    public List<VotingEntity> findQtByQid(int qid) {
        return votingRepository.findAllByQidAndBzIsNotNull(qid);
    }
}
