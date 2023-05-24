package com.yuanlrc.campus_market.controller.common;
/**
 * 图片统一查看控制器
 */

import com.yuanlrc.campus_market.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("photo")
@Controller
public class PhotoController {

    @Autowired
    private ResourceLoader resourceLoader;

    // @Value("${ylrc.upload.photo.path}")
    // private String uploadPhotoPath;//文件保存位置

    /**
     * 系统统一的图片查看方法
     *
     * @param filename
     * @return
     */
    @RequestMapping(value = "/view")
    @ResponseBody
    public ResponseEntity<?> viewPhoto(@RequestParam(name = "filename", required = true) String filename) {
        String uploadPhotoPath = PathUtil.newInstance().getUploadPhotoPath();
        Resource resource = resourceLoader.getResource("file:" + uploadPhotoPath + "/" + filename);
        try {
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
