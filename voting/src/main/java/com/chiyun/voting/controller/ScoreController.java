package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.entity.ScoringquestionEntity;
import com.chiyun.voting.entity.ScoringquestionoptionsEntity;
import com.chiyun.voting.service.ScoringServiceImpl;
import com.chiyun.voting.service.ThemeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api("打分管理")
@RestController
@RequestMapping(value = "/score", method = {RequestMethod.POST, RequestMethod.GET})
public class ScoreController {
    @Resource
    private ScoringServiceImpl scoringQService;
    @Resource
    private ThemeServiceImpl themeService;

    @RequestMapping("addScoreOP")
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
}
