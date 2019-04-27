package com.chiyun.voting.commons;

import com.chiyun.voting.utils.JWTUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionHelper {
    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        return request;
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) ra).getResponse();
        return response;
    }


    public static String getToken() {
        String token = getRequest().getHeader("accessToken");
        return token;
    }

    public static String getNewToken() {
        String token = JWTUtil.sign(getuname(), getuid(), getrole());
        return token;
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
