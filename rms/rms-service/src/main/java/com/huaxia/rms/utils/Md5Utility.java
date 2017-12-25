package com.huaxia.rms.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Utility {
    /**
     * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String salt = "(&^60L;9)()@098";
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Utility.class.getName() + "初始化失败，MessageDigest不支持WebUtility。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 适用于上G大的文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        String value = "";
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] cache = new byte[1048576];
        for (int i = in.read(cache); i != -1; i = in.read(cache)) {
            out.write(cache, 0, i);
        }
        in.close();
        out.close();
        messagedigest.update(out.toByteArray());
        value = bufferToHex(messagedigest.digest());
        return value;
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }


    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


    /**
     * 密码=密码+用户名
     * 加盐密码增加暴力破解的难度
     * @return
     */
    public static String md5SaltString(String passwd, String username) {
       return getMD5String(getMD5String(passwd+username) + salt);
    }

    /**
     * 密码=密码+用户名
     * 加盐密码增加暴力破解的难度
     * @return
     */
    public static String md5SaltString(String passwd, String username, String salt) {
        return getMD5String(getMD5String(passwd+username) + salt);
    }

    public static void main(String[] args) {
        System.out.println("salt:"+md5SaltString("123456", "11"));
        System.out.println("no salt:"+getMD5String("123456"));
    }

}
