package com.scutcms.util;


import java.util.Random;

/**
 * Created by anjouker on 16-6-17.
 */

/**
 *
 */
public class TokenHelper {
    private static char TokenPre[] = {
            '1','2','3','4','5','6','7','8','9','0',
            'a','b','c','d','e','f','g','h','i','j','k', 'l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };

    /**
     * 生成一串指定长度的随机字符串，字符串范围包括A-Z,a-z,0-9
     * @param length 字符串长度
     * @return 生成的字符串
     */
    public static String createRandomToken(int length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < length; i++){
            stringBuilder.append(TokenPre[random.nextInt(TokenPre.length)%(TokenPre.length)]);
        }
        return stringBuilder.toString();
    }
}
