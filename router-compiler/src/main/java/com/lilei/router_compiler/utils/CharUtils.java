package com.lilei.router_compiler.utils;

/**
 * Created by lilei
 * Date : 2018/9/27
 */

public class CharUtils {

    public static String firstCharUpperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
