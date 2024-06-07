package com.itheima.utils;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import jakarta.security.auth.message.AuthException;
import org.json.JSONException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

@Component
@ConfigurationProperties(prefix = "oss")
public class QiNiuUtil {
    private static final String accessUrl = "sejahcn79.hn-bkt.clouddn.com";
    private String accessKey ;
    private String secretKey ;

    private String bucket;

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String upload(String fileName, InputStream inputStream) throws JSONException, QiniuException {
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
        DefaultPutRet putRet = Json.decode(response.bodyString(), DefaultPutRet.class);
        return accessUrl + File.separator + putRet.key;
    }
}
