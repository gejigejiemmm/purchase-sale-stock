package cn.edu.zzuli.purchasesalestock.controller;

import cn.edu.zzuli.purchasesalestock.bean.Msg;
import cn.edu.zzuli.purchasesalestock.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/img")
public class ImgController {

    @Autowired
    private ImgService imgService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Msg addGoodsImg(MultipartFile file) {
        if (file != null) {
            //上传文件
            Map<String, String> map = imgService.uploadImg(file);
            if (map.get("res").equals("success")) {
                //上传成功返回图片URL
                return Msg.success().add("url",map.get("url"));
            }
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public void IoReadImage(String imgUrl , HttpServletResponse response)throws IOException {
        imgService.readImage(imgUrl,response);
    }
}
