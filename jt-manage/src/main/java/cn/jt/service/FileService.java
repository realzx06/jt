package cn.jt.service;

import cn.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ImageVO upload(MultipartFile multipartFile);

}
