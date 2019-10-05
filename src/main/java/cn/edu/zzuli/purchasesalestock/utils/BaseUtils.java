package cn.edu.zzuli.purchasesalestock.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BaseUtils {

    private BaseUtils(){}

    /**
     *
     * Description: 用于对密码进行MD5盐值加密
     *
     * @Title: Md5Encode
     *
     * @param password
     * @return String
     */
    public static String Md5Encode(String password) {
        String md5Password = "";
        try {
            // 获取MD5加密器
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加密次数
            for (int i = 0; i < 10; i++) {
                // 加密
                md5.update(password.getBytes());
                // 获取加密后的值
                byte[] result = md5.digest();
                StringBuffer buffer = new StringBuffer();
                // 进行盐值加密
                for (byte b : result) {
                    // 利用与运算
                    int number = b & 0xff;
                    String str = Integer.toHexString(number);
                    if (str.length() == 1) {
                        buffer.append("0");
                    } else {
                        buffer.append(str);
                    }
                }
                // 获取加密后的值
                md5Password = password = buffer.toString();
                // 处理下次要加密的密码 因为要加密十次
                password = password.substring(0, password.length() - 1);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Password;
    }

}
