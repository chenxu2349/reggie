package com.example.reggie.controller;

import com.example.reggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

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

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //后缀，文件类型
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，防止文件重名覆盖丢失数据
        String newFilename = UUID.randomUUID().toString() + suffix;
        //创建一个目录对象
        File dir = new File(basePath);
        if(!dir.exists()){
            //如果目录不存在
            dir.mkdirs();
        }

        try {
            //临时文件转存到指定位置
            file.transferTo(new File(basePath + newFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //给页面返回一个文件名，用于和菜品对应
        return Result.success(newFilename);
    }

    /**
     *@Description 文件下载
     *@Author chenxu
     *@Date 2022/6/6 20:26
     **/
    @GetMapping("/download")
    public void downloadFile(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //输出流，将文件回写到浏览器进行展示
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0, len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();

        }catch (Exception e) {}

        return;
    }
}
