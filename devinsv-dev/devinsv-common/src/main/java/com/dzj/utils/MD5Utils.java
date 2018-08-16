package com.dzj.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @author Devin13 on 2018/8/15.
 * @version 1.0
 */
public class MD5Utils {

    public static String getMD5(String value) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(value.getBytes()));
        return newstr;
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5("dzj");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
