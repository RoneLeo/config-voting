package com.chiyun.voting.service;

import com.chiyun.voting.entity.ScoringEntity;
import com.chiyun.voting.entity.ScoringquestionEntity;
import com.chiyun.voting.entity.ScoringquestionoptionsEntity;
import com.chiyun.voting.repository.ScoringQORepository;
import com.chiyun.voting.repository.ScoringQRepository;
import com.chiyun.voting.repository.ScoringRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ScoringServiceImpl {
    private final JdbcTemplate jdbcTemplate;
    private final ScoringQORepository scoringQORepository;
    private final ScoringQRepository scoringQRepository;
    private final ScoringRepository scoringRepository;

    public ScoringServiceImpl(JdbcTemplate jdbcTemplate, ScoringQORepository scoringQORepository, ScoringQRepository scoringQRepository, ScoringRepository scoringRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.scoringQORepository = scoringQORepository;
        this.scoringQRepository = scoringQRepository;
        this.scoringRepository = scoringRepository;
    }

    @Transactional
    public void saveAllScoreOp(List<ScoringquestionoptionsEntity> list) {
        String sql = "insert into scoringquestionoptions(themeid,scorelow,scorehign,scorename,scoredesc) values(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getHdid());
                ps.setInt(2, list.get(i).getZdf());
                ps.setInt(3, list.get(i).getZgf());
                ps.setString(4, list.get(i).getMc());
                ps.setString(5, list.get(i).getMcms());
            }

            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public ScoringquestionoptionsEntity saveScoreOp(ScoringquestionoptionsEntity entity) {
        return scoringQORepository.save(entity);
    }

    public int deleteScoreOp(ScoringquestionoptionsEntity entity) {
        return scoringQORepository.deleteAllById(entity.getId());
    }

    @Transactional
    public void saveAllScoreQ(List<ScoringquestionEntity> list) {
        String sql = "insert into scoringquestion(themeid,name,content) values(?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getHdid());
                ps.setString(2, list.get(i).getDx());
                ps.setString(3, list.get(i).getNr());
            }

            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public ScoringquestionEntity saveScoreDx(ScoringquestionEntity entity) {
        return scoringQRepository.save(entity);
    }

    public int deleteScoreDx(ScoringquestionEntity entity) {
        return scoringQRepository.deleteAllById(entity.getId());
    }

    @Transactional
    public void score(List<ScoringEntity> list) {
        String sql = "insert into scoring(pid,qid,oid,score) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getPid());
                ps.setInt(2, list.get(i).getQid());
                ps.setInt(3, list.get(i).getOid());
                ps.setInt(4, list.get(i).getDf());
            }

            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public int hasscore(int hdid, int uid) {
        return scoringQRepository.existsBypidAndHdid(hdid, uid);
    }

    public List<Map> findAllByPidAndQid(int pid, int qid) {
        return scoringRepository.findAllByPidAndQid(pid, qid);
    }

    public List<Map> getResultByQid(int qid) {
        return scoringRepository.getResultByQid(qid);
    }
}
