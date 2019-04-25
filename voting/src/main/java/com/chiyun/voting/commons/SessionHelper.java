package com.chiyun.voting.commons;

import com.chiyun.voting.utils.JWTUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SessionHelper {
    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        return request;
    }

    public static String getToken() {
        return getRequest().getHeader("accessToken");
    }

    public static String getuname() {
        try {
            return JWTUtil.getUsername(getToken());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getuid() {
        try {
            return JWTUtil.getUId(getToken());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getrole() {
        try {
            return JWTUtil.getRole(getToken());
        } catch (Exception e) {
            return null;
        }

    }
}
