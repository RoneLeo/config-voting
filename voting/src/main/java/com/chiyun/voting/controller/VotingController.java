package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.entity.ThemeEntity;
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

    @PostMapping("/addOne")
    @ApiOperation("添加一个投票对象")
    @MustLogin(rolerequired = 1)
    public ApiResult addOne(VotingquestionEntity votingquestionEntity, @RequestBody List<VotingquestionoptionsEntity> list, @RequestParam @ApiParam("是否添加其他") boolean qt) {
        if (!themeService.existById(votingquestionEntity.getHdid()))
            return ApiResult.FAILURE("不存在的活动");
        boolean boo;
        try {
            boo = votingService.saveVoting(votingquestionEntity, list, qt);
        } catch (Exception e) {
            return ApiResult.SUCCESS();
        }
        if (!boo)
            return ApiResult.FAILURE();
        return ApiResult.SUCCESS();
    }
}
