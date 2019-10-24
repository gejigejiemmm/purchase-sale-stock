package cn.edu.zzuli.purchasesalestock.service.impl;

import cn.edu.zzuli.purchasesalestock.service.ImgService;
import cn.edu.zzuli.purchasesalestock.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service(value = "ImgService")
public class ImgServiceImpl  implements ImgService {

    //注入配置文件application.yml中设置的图片存放子目录名
    @Value("${upload.path.Img}")
    private String IMG_PATH;

    /**
     * 上传以base64编码格式图片
     */
    @Override
    public Map<String,String> uploadImg(MultipartFile file){
        System.out.println(IMG_PATH);
        if (file != null){
            //上传文件
            Map<String,String> map = ImgUtils.saveMultFile(file,IMG_PATH);
            System.out.println("上传后"+map);
            return map;
        }else {
            Map<String,String> map = new HashMap<>();
            map.put("res","error");
            return map;
        }
    }

    /**
     * IO流读取图片
     * @param imgUrl 图片url，即图片保存在服务器上的名称
     */
    @Override
    public void readImage(String imgUrl, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String upload = null;

        //获取图片目录
        upload = ImgUtils.getPath(IMG_PATH);

        try {
            //获取图片存放路径
            String imgPath = upload + "/" + imgUrl;
            ips = new FileInputStream(new File(imgPath));
            String type = imgUrl.substring(imgUrl.indexOf(".") + 1);
            if (type.equals("png")) {
                response.setContentType("image/png");
            }
            if (type.equals("jpeg")) {
                response.setContentType("image/jpeg");
            }
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            ips.close();
        }
    }
}
