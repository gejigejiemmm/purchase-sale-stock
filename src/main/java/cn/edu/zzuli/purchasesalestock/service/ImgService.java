package cn.edu.zzuli.purchasesalestock.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ImgService {

    /**
     * 上传图片
     * @param file 传入图片文件
     * @return
     */
    Map<String,String> uploadImg(MultipartFile file);

    /**
     * 读取文件
     * @param imgUrl 图片 url
     * @param response
     * @throws IOException
     */
    public void readImage(String imgUrl, HttpServletResponse response) throws IOException;
}
