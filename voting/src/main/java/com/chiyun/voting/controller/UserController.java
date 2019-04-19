package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.entity.UserEntity;
import com.chiyun.voting.service.UserServiceImpl;
import com.chiyun.voting.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Api("用户管理")
@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ApiResult login(@RequestParam @ApiParam(value = "登录账号", required = true) String zh,
                           @RequestParam @ApiParam(value = "登录密码", required = true) String mm) {
        try {
            mm = MD5Util.MD5(mm);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE("请确认密码是否无误");
        }
        UserEntity userEntity = userService.findOneByZhAndMm(zh, mm);
        if (userEntity == null)
            return ApiResult.FAILURE("");
        return ApiResult.SUCCESS(userEntity);
    }

    @PostMapping("/add")
    @ApiOperation("用户登录")
    public ApiResult add(@RequestParam @ApiParam(value = "登录账号", required = true) String zh,
                         @RequestParam @ApiParam(value = "登录密码", required = true) String mm,
                         @RequestParam(required = false) @ApiParam(value = "用户姓名") String mc) {
        if (!userService.existsByZh(zh))
            return ApiResult.FAILURE("已存在该账号用户");
        UserEntity userEntity = new UserEntity();

        try {
            userEntity.setMm(MD5Util.MD5(mm));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.FAILURE("密码加密失败，请检查是否包含敏感词汇");
        }
        userEntity.setZh(zh);
        userEntity.setMc(mc);
        try {
            userService.save(userEntity);
            return ApiResult.SUCCESS(userEntity);
        } catch (
                Exception e) {
            return ApiResult.FAILURE();
        }
    }
}
