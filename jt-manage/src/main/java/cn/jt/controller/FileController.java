package cn.jt.controller;

import cn.jt.service.FileService;
import cn.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {


    @Autowired
    private FileService fileService;

    /*@RequestMapping("pic/upload")
    public ImageVO upload(MultipartFile uploadFile) {
        System.out.println("1111");
        return fileService.upload(uploadFile);
    }*/

}
