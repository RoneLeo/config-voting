package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.commons.SessionHelper;
import com.chiyun.voting.entity.ScoringEntity;
import com.chiyun.voting.entity.ScoringquestionEntity;
import com.chiyun.voting.entity.ScoringquestionoptionsEntity;
import com.chiyun.voting.entity.ThemeEntity;
import com.chiyun.voting.service.ScoringServiceImpl;
import com.chiyun.voting.service.ThemeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api("打分管理")
@RestController
@RequestMapping(value = "/score", method = {RequestMethod.POST, RequestMethod.GET})
public class ScoreController {
    @Resource
    private ScoringServiceImpl scoringQService;
    @Resource
    private ThemeServiceImpl themeService;

    @RequestMapping("addScoreOp")
    @ApiOperation("添加打分项目")
    @MustLogin(rolerequired = 1)
    public ApiResult addScoreOP(@RequestBody List<ScoringquestionoptionsEntity> list) {
        if (list == null || list.isEmpty())
            return ApiResult.FAILURE("无新增打分项目");
        ApiResult result = themeService.isfbscore(list.get(0).getHdid());
        if (result.getResCode() < 200)
            return result;
        try {
            scoringQService.saveAllScoreOp(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
        return ApiResult.SUCCESS();
    }

    @RequestMapping("updateScoreOneOp")
    @ApiOperation("添加或修改一个打分项目")
    @MustLogin(rolerequired = 1)
    public ApiResult addScoreOneOp(ScoringquestionoptionsEntity entity) {
        ApiResult result = themeService.isfbscore(entity.getHdid());
        if (result.getResCode() < 200)
            return result;
        try {
            scoringQService.saveScoreOp(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
        return ApiResult.SUCCESS();
    }

    @RequestMapping("deleteScoreOneOp")
    @ApiOperation("删除一个打分项目")
    @MustLogin(rolerequired = 1)
    public ApiResult deleteScoreOneOp(ScoringquestionoptionsEntity entity) {
        ApiResult result = themeService.isfbscore(entity.getHdid());
        if (result.getResCode() < 200)
            return result;
        try {
            scoringQService.deleteScoreOp(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
        return ApiResult.SUCCESS();
    }

    @RequestMapping("addScoreDx")
    @ApiOperation("添加打分对象")
    @MustLogin(rolerequired = 1)
    public ApiResult addScoreDx(@RequestBody List<ScoringquestionEntity> list) {
        if (list == null || list.isEmpty())
            return ApiResult.FAILURE("无新增打分对象");
        ApiResult result = themeService.isfbscore(list.get(0).getHdid());
        if (result.getResCode() < 200)
            return result;
        try {
            scoringQService.saveAllScoreQ(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
        return ApiResult.SUCCESS();
    }

    @RequestMapping("updateScoreOneDx")
    @ApiOperation("添加或修改一个打分对象")
    @MustLogin(rolerequired = 1)
    public ApiResult addScoreOneDx(ScoringquestionEntity entity) {
        ApiResult result = themeService.isfbscore(entity.getHdid());
        if (result.getResCode() < 200)
            return result;
        try {
            scoringQService.saveScoreDx(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
        return ApiResult.SUCCESS();
    }

    @RequestMapping("deleteScoreOneDx")
    @ApiOperation("删除一个打分对象")
    @MustLogin(rolerequired = 1)
    public ApiResult deleteScoreOneDx(ScoringquestionEntity entity) {
        ApiResult result = themeService.isfbscore(entity.getHdid());
        if (result.getResCode() < 200)
            return result;
        try {
            scoringQService.deleteScoreDx(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
        return ApiResult.SUCCESS();
    }

    @PostMapping("/score")
    @ApiOperation("打分")
    @MustLogin
    public ApiResult score(@RequestBody List<ScoringEntity> list) {
        if (list == null || list.isEmpty())
            return ApiResult.FAILURE("没有打分信息");
        ThemeEntity themeEntity = themeService.findByScoreId(list.get(0).getQid());
        if (themeEntity.getSffb() == 0)
            return ApiResult.FAILURE("打分活动未开启");
        else if (themeEntity.getSffb() == 2)
            return ApiResult.FAILURE("打分活动已结束");
        if (themeEntity.getJssj().before(new Date())) {
            themeEntity.setSffb(2);
            themeService.save(themeEntity);
            return ApiResult.FAILURE("打分活动已结束");
        }
        if (themeEntity.getKssj().after(new Date()))
            return ApiResult.FAILURE("打分活动暂未开始");
        if (scoringQService.hasscore(list.get(0).getQid(), SessionHelper.getuid()) > 0)
            return ApiResult.FAILURE("已参与该次打分");
        try {
            scoringQService.score(list);
            return ApiResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

    @MustLogin
    @ApiOperation("用户获取一个对象的打分情况")
    @RequestMapping("/getScoreByQid")
    public ApiResult getScoreByQidAndPid(@RequestParam @ApiParam(value = "打分对象id", required = true) int id) {
        List<Map> list = scoringQService.findAllByPidAndQid(SessionHelper.getuid(), id);
        return ApiResult.SUCCESS(list);
    }

    @MustLogin
    @ApiOperation("根据打分对象id获取对象得分情况")
    @RequestMapping("/getResultByQid")
    public ApiResult getResultByQid(@RequestParam @ApiParam(value = "打分对象id", required = true) int id) {
        List<Map> list = scoringQService.getResultByQid(id);
        return ApiResult.SUCCESS(list);
    }
}
