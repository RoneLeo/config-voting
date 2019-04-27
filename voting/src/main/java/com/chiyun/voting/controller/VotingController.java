package com.chiyun.voting.controller;

import com.alibaba.fastjson.JSON;
import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.entity.VotingquestionEntity;
import com.chiyun.voting.entity.VotingquestionoptionsEntity;
import com.chiyun.voting.service.ThemeServiceImpl;
import com.chiyun.voting.service.VotingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("投票类型活动管理")
@RestController
@RequestMapping(value = "/voting", method = {RequestMethod.POST, RequestMethod.GET})
public class VotingController {

    @Resource
    private ThemeServiceImpl themeService;
    @Resource
    private VotingServiceImpl votingService;

    @PostMapping("/addOneVoting")
    @ApiOperation("添加一个投票题目")
    @MustLogin(rolerequired = 1)
    public ApiResult addOne(VotingquestionEntity votingquestionEntity, String list, @RequestParam @ApiParam("是否添加其他") boolean qt) {
        ApiResult result = themeService.isfbvote(votingquestionEntity.getHdid());
        if (result.getResCode() < 200)
            return result;
        votingquestionEntity.setId(0);
        boolean boo;
        try {
            List<VotingquestionoptionsEntity> lisdata = JSON.parseArray(list, VotingquestionoptionsEntity.class);
            boo = votingService.saveVoting(votingquestionEntity, lisdata, qt);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.SUCCESS();
        }
        if (!boo)
            return ApiResult.FAILURE();
        return ApiResult.SUCCESS();
    }

    @PostMapping("/updateOne")
    @ApiOperation("修改一个投票题目")
    @MustLogin(rolerequired = 1)
    public ApiResult updateOne(VotingquestionEntity votingquestionEntity) {
        ApiResult result = themeService.isfbvote(votingquestionEntity.getHdid());
        if (result.getResCode() < 200)
            return result;
        if (!votingService.existById(votingquestionEntity.getId()))
            return ApiResult.FAILURE("不存在的投票");
        try {
            votingService.save(votingquestionEntity);
            return ApiResult.SUCCESS(votingquestionEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

    @PostMapping("/deleteOne")
    @ApiOperation("删除一个投票对象")
    @MustLogin(rolerequired = 1)
    public ApiResult deleteOne(VotingquestionEntity votingquestionEntity) {
        ApiResult result = themeService.isfbvote(votingquestionEntity.getHdid());
        if (result.getResCode() < 200)
            return result;
        if (!votingService.existById(votingquestionEntity.getId()))
            return ApiResult.FAILURE("不存在的投票");
        try {
            int sum = votingService.deleteById(votingquestionEntity.getId());
            if (sum >= 1)
                return ApiResult.SUCCESS();
            else
                return ApiResult.FAILURE();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

//    @PostMapping("/addOneOption")
//    @ApiOperation("新增一个投票选项")
//    @MustLogin(rolerequired = 1)
//    public ApiResult addOneOption(VotingquestionoptionsEntity votingquestionoptionsEntity) {
//        VotingquestionEntity votingquestionEntity = votingService.findById(votingquestionoptionsEntity.getDxid());
//        if (votingquestionEntity == null)
//            return ApiResult.FAILURE("不存在的投票");
//        ApiResult result = themeService.isfbvote(votingquestionEntity.getHdid());
//        if (result.getResCode() < 200)
//            return result;
//        votingService.saveVQO(votingquestionoptionsEntity);
//        return ApiResult.SUCCESS();
//    }

    @PostMapping("/updateOneOption")
    @ApiOperation("新增或修改一个投票选项")
    @MustLogin(rolerequired = 1)
    public ApiResult updateOneOption(VotingquestionoptionsEntity votingquestionoptionsEntity) {
        VotingquestionEntity votingquestionEntity = votingService.findById(votingquestionoptionsEntity.getDxid());
        if (votingquestionEntity == null)
            return ApiResult.FAILURE("不存在的投票");
        ApiResult result = themeService.isfbvote(votingquestionEntity.getHdid());
        if (result.getResCode() < 200)
            return result;
        votingService.saveVQO(votingquestionoptionsEntity);
        return ApiResult.SUCCESS();
    }

    @PostMapping("/deleteOneOption")
    @ApiOperation("删除一个投票选项")
    @MustLogin(rolerequired = 1)
    public ApiResult deleteOneOption(VotingquestionoptionsEntity votingquestionoptionsEntity) {
        VotingquestionEntity votingquestionEntity = votingService.findById(votingquestionoptionsEntity.getDxid());
        if (votingquestionEntity == null)
            return ApiResult.FAILURE("不存在的投票");
        ApiResult result = themeService.isfbvote(votingquestionEntity.getHdid());
        if (result.getResCode() < 200)
            return result;
        votingService.deleteVQO(votingquestionoptionsEntity.getId());
        return ApiResult.SUCCESS();
    }
}
