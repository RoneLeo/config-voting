package com.chiyun.voting.controller;

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
import java.util.ArrayList;
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
    @ApiOperation("添加一个投票对象")
    @MustLogin(rolerequired = 1)
    public ApiResult addOne(VotingquestionEntity votingquestionEntity, @RequestBody List<VotingquestionoptionsEntity> list, @RequestParam @ApiParam("是否添加其他") boolean qt) {
        if (!themeService.existById(votingquestionEntity.getHdid()))
            return ApiResult.FAILURE("不存在的活动");
        votingquestionEntity.setId(0);
        boolean boo;
        try {
            boo = votingService.saveVoting(votingquestionEntity, list, qt);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.SUCCESS();
        }
        if (!boo)
            return ApiResult.FAILURE();
        return ApiResult.SUCCESS();
    }

    @PostMapping("/updateOne")
    @ApiOperation("修改一个投票对象")
    @MustLogin(rolerequired = 1)
    public ApiResult updateOne(VotingquestionEntity votingquestionEntity) {
        if (!votingService.existById(votingquestionEntity.getHdid()))
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
    public ApiResult deleteOne(@RequestParam @ApiParam(value = "投票题目id", required = true) int id) {
        if (!votingService.existById(id))
            return ApiResult.FAILURE("不存在的投票");
        try {
            int sum = votingService.deleteById(id);
            if (sum >= 1)
                return ApiResult.SUCCESS();
            else
                return ApiResult.FAILURE();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

}
