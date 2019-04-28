package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.commons.MustLogin;
import com.chiyun.voting.commons.SessionHelper;
import com.chiyun.voting.entity.ThemeEntity;
import com.chiyun.voting.entity.VotingquestionEntity;
import com.chiyun.voting.service.ThemeServiceImpl;
import com.chiyun.voting.service.VotingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Api("活动管理")
@RestController
@RequestMapping(value = "/theme", method = {RequestMethod.POST, RequestMethod.GET})
public class ThemeController {
    @Resource
    private ThemeServiceImpl themeService;
    @Resource
    private VotingServiceImpl votingService;

    @PostMapping("/add")
    @ApiOperation("活动添加")
    @MustLogin(rolerequired = 1)
    public ApiResult add(ThemeEntity themeEntity) {
        return themeService.addOne(themeEntity);
    }

    @PostMapping("/update")
    @ApiOperation("活动修改")
    @MustLogin(rolerequired = 1)
    public ApiResult update(ThemeEntity themeEntity) {
        ThemeEntity entity = themeService.findById(themeEntity.getId());
        if (entity == null)
            return ApiResult.FAILURE("不存在的活动");
        try {
            themeService.save(themeEntity);
            return ApiResult.SUCCESS(themeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

    @PostMapping("/delete")
    @ApiOperation("活动删除")
    @MustLogin(rolerequired = 1)
    public ApiResult<Object> delete(@RequestParam @ApiParam(value = "活动id", required = true) int hdid) {
        ThemeEntity themeEntity = themeService.findById(hdid);
        if (themeEntity == null)
            return ApiResult.FAILURE("不存在的活动");
        if (themeEntity.getSffb() != 0)
            return ApiResult.FAILURE("已发布的活动不能删除");
        int sum = themeService.deleteById(hdid);
        if (sum == 1)
            return ApiResult.SUCCESS();
        return ApiResult.FAILURE();
    }

    @PostMapping("/findAllInfoById")
    @ApiOperation("根据活动id查询所有信息")
    @MustLogin
    public ApiResult findAllInfoById(@RequestParam @ApiParam(value = "活动id", required = true) int id) {
        ThemeEntity entity = themeService.findAllInfoById(id, SessionHelper.getuid());
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

    @RequestMapping("/verify")
    @ApiOperation("活动状态验证")
    @MustLogin
    public ApiResult verify(@RequestParam @ApiParam(value = "活动id", required = true) int hdid) {
        ThemeEntity themeEntity = themeService.findById(hdid);
        if (themeEntity.getSffb() == 0)
            return ApiResult.FAILURE("活动未开启");
        else if (themeEntity.getSffb() == 2)
            return ApiResult.FAILURE("活动已结束");
        if (themeEntity.getKssj().after(new Date()))
            return ApiResult.FAILURE("活动未开始");
        if (themeEntity.getJssj().before(new Date())) {
            themeEntity.setSffb(2);
            themeService.save(themeEntity);
            return ApiResult.FAILURE("活动已结束");
        }
        if (themeEntity.getHdlx() == 0) {
            return ApiResult.SUCCESS();
        } else {
            return ApiResult.SUCCESS();
        }
    }

    @RequestMapping("/getResult")
    @ApiOperation("获取活动结果")
//    @MustLogin
    public ApiResult getResult(@RequestParam @ApiParam(value = "活动id", required = true) int hdid) {
        ThemeEntity themeEntity = themeService.findById(hdid);
        if (themeEntity.getSffb() == 0) {
            return ApiResult.FAILURE("活动未开启");
        } else if (themeEntity.getSffb() == 1 && themeEntity.getJssj().after(new Date())) {
            return ApiResult.FAILURE("活动未结束");
        }
        if (themeEntity.getKssj().after(new Date()))
            return ApiResult.FAILURE("活动未开始");
        if (themeEntity.getJssj().before(new Date())) {
            themeEntity.setSffb(2);
            themeService.save(themeEntity);
        }
        if (themeEntity.getHdlx() == 1) {
            List<Map> list = themeService.getScoreResult(themeEntity.getId());
            Map map = new HashMap();
            map.put("hddata", themeEntity);
            map.put("result", list);
            return ApiResult.SUCCESS(map);
        } else {
            List<Map> list = new ArrayList<>();
            List<Map> entityList = votingService.findAllIdByHdid(themeEntity.getId());
            for (Map entity : entityList) {
                List<Map> rlist = themeService.getVoteResult((Integer) entity.get("id"));
                Map entitymap = new HashMap();
                entitymap.put("vote", entity);
                entitymap.put("result", rlist);
                list.add(entitymap);
            }
            Map map = new HashMap();
            map.put("hddata", themeEntity);
            map.put("result", list);
            return ApiResult.SUCCESS(map);
        }

    }
}
