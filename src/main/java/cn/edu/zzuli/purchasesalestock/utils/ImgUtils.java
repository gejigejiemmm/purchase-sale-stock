package cn.edu.zzuli.purchasesalestock.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImgUtils {


    public static String  getPath(String subdirectory){
        //获取跟目录---与jar包同级目录的upload目录下指定的子目录subdirectory
        File upload = null;
        try {
            //本地测试时获取到的是"工程目录/target/upload/subdirectory
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            upload = new File(path.getAbsolutePath(),subdirectory);
            if(!upload.exists()) upload.mkdirs();//如果不存在则创建目录
            String realPath = upload + "/";
            return realPath;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取服务器路径发生错误！");
        }
    }

    //以base64编码格式上传，将照片转成字节流
    public static Map<String,String> getImg(String imageFile, String subdirectory){
        // 通过base64来转化图片
        String type = imageFile.substring(imageFile.indexOf("/")+1,imageFile.indexOf(";"));
        if (type.equals("png")){
            imageFile = imageFile.replaceAll("data:image/png;base64,", "");
        }
        if (type.equals("jpeg")){
            imageFile = imageFile.replaceAll("data:image/jpeg;base64,", "");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码
        byte[] imageByte = null;
        try {
            imageByte = decoder.decodeBuffer(imageFile);
            for (int i = 0; i < imageByte.length; ++i) {
                if (imageByte[i] < 0) {// 调整异常数据
                    imageByte[i] += 256;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        type = "." + type;
        return saveImg(imageByte,subdirectory,type);
    }

    //存储照片到服务器
    private static Map<String,String> saveImg(byte[] imageByte,String subdirectory,String type){
        // 生成文件名及文件类型
        String files = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date())
                + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
                + type;

        Map<String,String> map = new HashMap<>();
        //获取跟目录---与jar包同级目录并生成文件路径
        String filename = getPath(subdirectory) + files;
        try {
            // 生成文件
            File imageFile = new File(filename);
            imageFile.createNewFile();
            if(!imageFile.exists()){
                imageFile.createNewFile();
            }
            OutputStream imageStream = new FileOutputStream(imageFile);
            imageStream.write(imageByte);
            imageStream.flush();
            imageStream.close();
            map.put("res","success");
            map.put("url",files);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("res","error");
            return map;
        }
    }

    //以MultipartFile方式上传到服务器
    public static Map<String,String> saveMultFile(MultipartFile file, String subdirectory){
        //上传文件路径
        String path = getPath(subdirectory);
        System.out.println("saveImgUnit---------"+path);
        //重新修改文件名防止重名
        String filename = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date())
                + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
                + file.getOriginalFilename();
        File filepath = new File(path, filename);
        //判断路径是否存在，没有就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文档中
        Map<String ,String> map = new HashMap<>();
        File file1 = new File(path + File.separator + filename);
        try {
            file.transferTo(file1);
            map.put("res","success");
            map.put("url",filename);
            return map;
        } catch (IOException e) {
            map.put("res","error");
            return map;
        }
    }

}
