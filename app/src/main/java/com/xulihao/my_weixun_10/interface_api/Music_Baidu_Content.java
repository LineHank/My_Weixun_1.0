package com.xulihao.my_weixun_10.interface_api;



import com.xulihao.my_weixun_10.bean.Music_Content_Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 濠 on 2016/11/14.
 */

public interface Music_Baidu_Content {
    @GET("ting")
    Call<Music_Content_Bean> getString(@Query("from") String from, @Query("method") String method,
                                       @Query("format") String format, @Query("callback") String callback,
                                       @Query("songid") String songid);
}
