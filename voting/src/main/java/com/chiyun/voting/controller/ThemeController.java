package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.commons.SessionHelper;
import com.chiyun.voting.entity.ThemeEntity;
import com.chiyun.voting.service.ThemeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ApiResult<Object> delete(@RequestParam @ApiParam(value = "活动id", required = true) int hdid) {
        int sum = themeService.deleteById(hdid);
        if (sum == 1)
            return ApiResult.SUCCESS();
        return ApiResult.FAILURE();
    }

    @PostMapping("/findAllInfoById")
    @ApiOperation("根据活动id查询所有信息")
    public ApiResult<ThemeEntity> findAllInfoById(@RequestParam @ApiParam(value = "活动id", required = true) int id) {
        ThemeEntity entity = themeService.findAllInfoById(id);
        return ApiResult.SUCCESS(entity);
    }

    @PostMapping("/findAll")
    @ApiOperation("查询所有活动")
    @MustLogin
    public ApiResult findAllByFbzt() {
        Map<String, List<ThemeEntity>> map = new HashMap<>();

        List<ThemeEntity> list1 = themeService.findAllByFbzt(1);
        List<ThemeEntity> list2 = themeService.findAllByFbzt(2);

        map.put("yfb", list1);
        map.put("ywc", list2);
        if (SessionHelper.getrole() == null || SessionHelper.getrole() != 0) {
            List<ThemeEntity> list0 = themeService.findAllByFbzt(0);
            map.put("wfb", list0);
        }
        return ApiResult.SUCCESS(map);
    }

    @PostMapping("/updateFbzt")
    @ApiOperation("修改活动状态：0-未发布，1-已发布，2-已结束")
    public ApiResult updateFbzt(@RequestParam @ApiParam(value = "活动id", required = true) int id,
                                @RequestParam @ApiParam(value = "状态", required = true) int fbzt) {
        ThemeEntity themeEntity = themeService.findById(id);
        if (themeEntity == null)
            return ApiResult.FAILURE("不存在的活动");
        if (themeEntity.getSffb() == fbzt)
            return ApiResult.SUCCESS();
        int sum = themeService.updateFbzt(id, fbzt);
        if (sum > 0)
            return ApiResult.SUCCESS();
        return ApiResult.FAILURE();
    }
}
