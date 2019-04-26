package com.chiyun.voting.controller;

import com.chiyun.voting.commons.ApiResult;
import com.chiyun.voting.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@Api("文件管理")
@RestController
@RequestMapping(value = "/files", method = {RequestMethod.POST, RequestMethod.GET})
public class FilesController {
    @Value("${uploadpath}")
    String filepath;

    @PostMapping("/uploadOne")
    public ApiResult uploadOne(MultipartFile file) {
        if (file == null)
            return ApiResult.FAILURE();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String realPath = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        File dest = new File(filepath + realPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return ApiResult.SUCCESS(realPath);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.FAILURE();
        }
    }

    @PostMapping("/{filename}")
    public void getOne(@PathVariable String filename, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(filename))
            return;
        String path = filepath + filename;
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        String userAgent = request.getHeader("USER-AGENT");
        String newFilename = URLEncoder.encode(filename, "UTF-8").replace("+", " ");
        if (userAgent != null) {
            if (userAgent.contains("edge") || userAgent.contains("Edge") || userAgent.contains("Trident") || userAgent.contains("trident")) {
                newFilename = URLEncoder.encode(filename, "UTF-8").replace("+", " ");
            } else {
                newFilename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
            }
        }
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + newFilename);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
