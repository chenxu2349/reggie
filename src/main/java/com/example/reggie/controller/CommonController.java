package com.example.reggie.controller;

import com.example.reggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName CommonController
 * @Description
 * @Author chenxu
 * @Date 2022/6/5 11:29
 **/
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    //从配置文件里动态取值
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * chenxu
     * 2022/6/5 11:30
     **/
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file){
        //！！注意，这里的参数名file是和前端保持一致的
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info(file.toString());

        try {
            //临时文件转存到指定位置
            file.transferTo(new File(basePath + "hello.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
