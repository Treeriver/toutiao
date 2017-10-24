package com.nowcoder.service;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.controller.LoginController;
import com.nowcoder.util.ToutiaoUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/28.
 */
@Service
public class QiniuService {
    private static final Logger logger= LoggerFactory.getLogger(QiniuService.class);
    //构造一个带指定Zone对象的配置类
    //...其他参数参考类注释
    //...生成上传凭证，然后准备上传
    String accessKey = "6l8t0uRU4IkcFcfinAdVl6ljxWmDvwpCTlywab0a";
    String secretKey = "EGJb2TSNj0K01yLfUnXZxbBrMcOYaOeUQObyQBvW";
    String bucketname = "nowcoder";

    //构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone1());
    //...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);
    Auth auth = Auth.create(accessKey, secretKey);

    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            int dotPos= file.getOriginalFilename().lastIndexOf(".");
            if(dotPos<0){
                return null;
            }
            String fileExt=file.getOriginalFilename().substring(dotPos+1).toLowerCase();
            if(!ToutiaoUtil.isFileAllowed(fileExt)){
                return null;
            }
            String fileName= UUID.randomUUID().toString().replaceAll("-","")+"."+fileExt;
            Response response = uploadManager.put(file.getBytes(),fileName,getUpToken());
            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
            //System.out.println(response.bodyString());
            if(response.isOK()&& response.isJson()) {
                String key = JSONObject.parseObject(response.bodyString()).get("key").toString();
                return ToutiaoUtil.QINIU_DOMAIN_PREFIX + key;
            }else{
                logger.error("七牛异常"+ response.bodyString());
                return null;
            }
        } catch (QiniuException ex) {
           logger.error("七牛异常"+ ex.getMessage());
           return null;
        }
    }
}
