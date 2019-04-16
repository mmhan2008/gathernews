package com.cnstock.service;

import com.cnstock.utils.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author user01
 * @create 2019/2/27
 */
@Service
public class UploadServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    public boolean upload(MultipartFile file){

        String path = UploadUtil.uploadFile(file);
        if (StringUtils.isEmpty(path)){
            logger.info("文件上传失败！");
            return false;
        }
        return true;
    }
}

