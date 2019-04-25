package com.chiyun.voting.service;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.entity.ThemeEntity;
import com.chiyun.voting.repository.ThemeRepository;
import com.chiyun.voting.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ThemeServiceImpl {
    @Resource
    private ThemeRepository themeRepository;

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
}
