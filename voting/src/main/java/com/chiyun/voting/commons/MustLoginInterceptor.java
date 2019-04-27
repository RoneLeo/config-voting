package com.chiyun.voting.commons;

import com.chiyun.voting.utils.JWTUtil;
import com.chiyun.voting.utils.MessageUtils;
import com.chiyun.voting.utils.StringUtils;
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
        if (annotation != null) {
            if (!JWTUtil.verify(SessionHelper.getToken())) {
                MessageUtils.resultMsg(response, ApiResult.UNKNOWN());
                return false;
            }
            if (StringUtils.datecompare(JWTUtil.getdate(SessionHelper.getToken()), 20)) {
                response.setHeader("accessToken", SessionHelper.getNewToken());
            }
            Integer uid = SessionHelper.getuid();
            Integer role = SessionHelper.getrole();
            int[] needs = annotation.rolerequired();
            if (uid == null || uid == 0) {
                MessageUtils.resultMsg(response, ApiResult.UNKNOWN());
                return false;
            }
            for (int need : needs) {
                //todo 权限判断
                if (need == 0 || (role == need)) {
                    return true;
                }
            }
            MessageUtils.resultMsg(response, ApiResult.FAILURE("您没有权限进行此操作"));
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("accessToken", SessionHelper.getToken());
        super.afterCompletion(request, response, handler, ex);
    }
}
