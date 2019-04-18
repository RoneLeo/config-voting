package com.chiyun.voting.commons;

import com.chiyun.voting.utils.MessageUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MustLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (Exception e) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        MustLogin annotation = method.getAnnotation(MustLogin.class);
        Object entity = SessionHelper.getuser();
        if (annotation != null) {
            int[] needs = annotation.rolerequired();
            if (entity == null) {
                MessageUtils.resultMsg(response, ApiResult.UNKNOWN());
                return false;
            }
//            if (entity.getJs() != 1 && entity.getDlsx() != null && entity.getDlsx().before(new Date())) {
//                MessageUtils.resultMsg(response, ApiResult.TimeDown());
//                return false;
//            }
            for (int need : needs) {
                //todo 权限判断
                if (need == 0 || (1 == need)) {
                    return true;
                }
            }
            MessageUtils.resultMsg(response, ApiResult.FAILURE("您没有权限进行此操作"));
            return false;
        }
        return true;
    }

}
