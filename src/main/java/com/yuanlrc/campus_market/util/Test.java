package com.yuanlrc.campus_market.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Description:
 *
 * datetime:2021/2/25 9:25
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        //获取classes路径
        //ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "upload/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        String uploadPath = upload.getAbsolutePath();
    }
}
