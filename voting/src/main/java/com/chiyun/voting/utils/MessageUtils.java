package com.chiyun.voting.utils;

import com.alibaba.fastjson.JSONObject;
import com.chiyun.voting.commons.ApiResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageUtils {
    public static void resultMsg(HttpServletResponse response, ApiResult<Object> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();
        String resultjson = JSONObject.toJSONString(result);
        pw.write(resultjson);
    }
}
