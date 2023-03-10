package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 千亦
 * @create 2022-11-17-9:33
 */
@RestController
public class uploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile){
        ResponseResult result = uploadService.uploadImg(multipartFile);
        return result;
    }
}
