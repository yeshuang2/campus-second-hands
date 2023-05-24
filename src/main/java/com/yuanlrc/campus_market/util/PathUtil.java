package com.yuanlrc.campus_market.util;


import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Description:
 *
 * datetime:2021/2/23 10:35
 */
public class PathUtil {
    public static PathUtil newInstance() {
        PathUtil instance = new PathUtil();
        return instance;
    }

    //获取上传路径
    public String getUploadPhotoPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        //如果上传目录为src/main/resources/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "src/main/resources/upload/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        String uploadPath = upload.getAbsolutePath();
        return uploadPath;
    }

    //获取备份路径
    public String getBackUpDir() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        //如果备份目录为src/main/resources/backup/,则可以如下获取
        File backup = new File(path.getAbsolutePath(), "src/main/resources/backup/");
        if (!backup.exists()) {
            backup.mkdirs();
        }
        String backUpDir = backup.getAbsolutePath();
        return backUpDir;
    }

}
