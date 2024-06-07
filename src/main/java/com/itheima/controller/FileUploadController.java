package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.QiNiuUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @Autowired
    private QiNiuUtil qiNiuUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        String fileUrl = null;
        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                fileUrl = qiNiuUtil.upload(fileName, file.getInputStream());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            return Result.error("文件不存在");
        }
        return Result.success(fileUrl);
    }
}
