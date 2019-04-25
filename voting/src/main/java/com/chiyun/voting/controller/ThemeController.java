package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.entity.ThemeEntity;
import com.chiyun.voting.service.ThemeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("活动管理")
@RestController
@RequestMapping(value = "/theme", method = {RequestMethod.POST, RequestMethod.GET})
public class ThemeController {
    @Resource
    private ThemeServiceImpl themeService;

    @PostMapping("/add")
    @ApiOperation("活动添加")
    @MustLogin(rolerequired = 1)
    public ApiResult add(ThemeEntity themeEntity) {
        return themeService.save(themeEntity);
    }

    @PostMapping("/delete")
    @ApiOperation("活动删除")
    @MustLogin(rolerequired = 1)
    public ApiResult delete(@RequestParam @ApiParam(value = "活动id", required = true) int hdid) {
        int sum = themeService.deleteById(hdid);
        if (sum == 1)
            return ApiResult.SUCCESS();
        return ApiResult.FAILURE();
    }
}
