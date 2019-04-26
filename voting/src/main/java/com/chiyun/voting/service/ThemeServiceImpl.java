package com.chiyun.voting.service;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.entity.ScoringquestionEntity;
import com.chiyun.voting.entity.ScoringquestionoptionsEntity;
import com.chiyun.voting.entity.ThemeEntity;
import com.chiyun.voting.entity.VotingquestionEntity;
import com.chiyun.voting.repository.ScoringQORepository;
import com.chiyun.voting.repository.ScoringQRepository;
import com.chiyun.voting.repository.ThemeRepository;
import com.chiyun.voting.repository.VotingquestionRepository;
import com.chiyun.voting.utils.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl {
    @Resource
    private ThemeRepository themeRepository;
    @Resource
    private VotingquestionRepository votingquestionRepository;
    @Resource
    private ScoringQRepository scoringQRepository;
    @Resource
    private ScoringQORepository scoringQORepository;
    @Resource
    private JdbcTemplate jdbcTemplate;

    public ApiResult save(ThemeEntity themeEntity) {
        if (StringUtils.isEmpty(themeEntity.getBt()))
            return ApiResult.FAILURE("标题不能为空");
        themeEntity.setId(0);
        if (themeEntity.getHdlx() == 0)
            themeEntity.setHdlx(0);
        else
            themeEntity.setHdlx(1);
        if (themeEntity.getKssj() != null) {
            themeEntity.setKssj(StringUtils.getDayBegin(themeEntity.getKssj()));
        }
        if (themeEntity.getJssj() != null) {
            themeEntity.setJssj(StringUtils.getDayEnd(themeEntity.getJssj()));
        }
        if (themeEntity.getSffb() == 1)
            themeEntity.setSffb(1);
        else
            themeEntity.setSffb(0);
        themeEntity.setSfnm(0);
        try {
            themeRepository.save(themeEntity);
            return ApiResult.SUCCESS(themeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

    public int deleteById(int id) {
        return themeRepository.deleteAllById(id);
    }

    public boolean existById(int id) {
        return themeRepository.existsById(id);
    }

    public ThemeEntity findById(int id) {
        Optional<ThemeEntity> optional = themeRepository.findById(id);
        return optional.orElse(null);
    }

    public ThemeEntity findAllInfoById(int id) {
        Optional<ThemeEntity> optional = themeRepository.findById(id);
        if (!optional.isPresent())
            return null;
        ThemeEntity entity = optional.get();
        if (entity.getHdlx() == 0) {
            List<VotingquestionEntity> list = votingquestionRepository.findAllByHdid(entity.getId());
            entity.setVotelist(list);
        } else {
            List<ScoringquestionEntity> slist = scoringQRepository.findAllByHdid(entity.getId());
            List<ScoringquestionoptionsEntity> olist = scoringQORepository.findAllByHdid(entity.getId());
            entity.setScorelist(slist);
            entity.setScoreoblist(olist);
        }
        return entity;
    }

    public List<ThemeEntity> findAllByFbzt(int fbzt) {
        String sql;
        if (fbzt == 0) {
            sql = "select * from theme where publish = 0 ";
        } else if (fbzt == 1) {
            sql = "select * from theme where publish = 1 order by etime ASC ";
        } else {
            sql = "select * from theme where publish = 2 order by etime desc ";
        }
        final List<ThemeEntity> forumList = new ArrayList<ThemeEntity>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {//将结果集中的数据映射到List中
            public void processRow(ResultSet rs) throws SQLException {
                ThemeEntity forum = new ThemeEntity();
                forum.setId(rs.getInt("id"));
                forum.setBt(rs.getString("title"));
                forum.setNr(rs.getString("content"));
                forum.setHdlx(rs.getInt("vtype"));
                forum.setSffb(rs.getInt("publish"));
                forum.setKssj(rs.getDate("btime"));
                forum.setJssj(rs.getDate("etime"));
                forum.setSfnm(rs.getInt("anonymous"));
                forumList.add(forum);
            }
        });
        return forumList;
    }

    public int updateFbzt(int id, int fbzt) {
        return themeRepository.updateFbzt(id, fbzt);
    }

    public ApiResult isfbvote(int hdid) {
        ThemeEntity themeEntity = findById(hdid);
        if (themeEntity == null) {
            return ApiResult.FAILURE("不存在的活动");
        }
        if (themeEntity.getSffb() != 0) {
            return ApiResult.FAILURE("已发布的投票活动不能修改");
        }
        return ApiResult.SUCCESS();
    }

    public ApiResult isfbscore(int hdid) {
        ThemeEntity themeEntity = findById(hdid);
        if (themeEntity == null) {
            return ApiResult.FAILURE("不存在的活动");
        }
        if (themeEntity.getSffb() != 0) {
            return ApiResult.FAILURE("已发布的打分活动不能修改");
        }
        return ApiResult.SUCCESS();
    }
}
