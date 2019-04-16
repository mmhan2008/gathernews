package com.cnstock.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author user01
 * @create 2019/2/27
 */
public class UploadUtil {

    private static Logger log = LoggerFactory.getLogger(UploadUtil.class);

    private static String path = System.getProperty("user.dir") + "/file";

    public static String uploadFile(MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传文件名：" + fileName);
        log.info(path);
        String subFix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        // 保存时文件名字
        String name = "info";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String realPath = null;
        try {
            file.transferTo(new File(f + "\\" + name + "." + subFix));
            realPath = f + "\\" + name + "." + subFix;
            log.info("真实路径：" + realPath);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realPath;
    }
}

