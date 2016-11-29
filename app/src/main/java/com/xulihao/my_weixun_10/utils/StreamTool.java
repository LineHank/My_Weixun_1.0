package com.xulihao.my_weixun_10.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by 濠 on 2016/11/19.
 */

public class StreamTool {
    public static byte[] getBytes(InputStream is) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = is.read(buffer))!=-1){
            bos.write(buffer, 0, len);
        }
        is.close();
        bos.flush();
        byte[] result = bos.toByteArray();
        return  result;
    }
}
