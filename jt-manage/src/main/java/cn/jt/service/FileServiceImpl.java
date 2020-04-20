package cn.jt.service;

import cn.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
    @Override
    public ImageVO upload(MultipartFile multipartFile) {
        return null;
    }

    @Value("${image.localDir}")
    private String localDir; //= "E:/JT_IMAGE/"; //定义根目录
 //   private String localDir="E:/cgb11/jtimages/";
    @Value("${image.urlPath}")
    private String urlPath;//="http://image.jt.com/";

  /* @Override
    public ImageVO upload(MultipartFile uploadFile) {

     //1.获取图片名称  a.jpg
        String fileName = uploadFile.getOriginalFilename();
        //2.将图片名称都转化为小写字母
        fileName = fileName.toLowerCase();
        //3.如果判断字符串是否满足某种要求,则正则首选.
        if(!fileName.matches("^.+\\.(png|jpg|gif)$")) {

            return ImageVO.fail();
        }

        //4.判断是否为恶意程序 通过宽度和高度进行判断
        try {
            BufferedImage bufferedImage =
                    ImageIO.read(uploadFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            if(width ==0 || height ==0) {
                //说明 上传的不是图片,为恶意程序.
                return ImageVO.fail();
            }

            //5.如果程序执行到这里,则表示用户上传的确实是图片
            //按照时间将目录进行划分 yyyy/MM/dd
            String deteDir = new SimpleDateFormat("yyyy/MM/dd/")
                    .format(new Date());

            //6.准备文件目录  E:/JT_IMAGE/yyyy/MM/dd/
            String localFileDir = localDir + deteDir;
            File fileDir = new File(localFileDir);
            if(!fileDir.exists()) { //如果当前的目录不存在,应该创建目录
                //E:image/a/b/c
                //fileDir.mkdir();  //创建一级目录
                fileDir.mkdirs();   //创建多级目录
            }

            //7.动态生成文件名称 uuid.jpg
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //abc.jpg
            int index = fileName.lastIndexOf(".");
            String fileType = fileName.substring(index);
            String realFileName = uuid + fileType;

            //8.实现真实的文件上传 磁盘路径+文件名称
            String realFilePath = localFileDir + realFileName;
            File imageFile = new File(realFilePath);
            uploadFile.transferTo(imageFile);

            String url = urlPath+deteDir+realFileName;
            return ImageVO.success(url, width, height);

        } catch (IOException e) {

            e.printStackTrace();
            return ImageVO.fail();  //图片上传失败
        }
    }*/
}
