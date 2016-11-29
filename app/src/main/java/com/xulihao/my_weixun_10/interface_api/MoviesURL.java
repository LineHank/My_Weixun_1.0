package com.xulihao.my_weixun_10.interface_api;


import com.xulihao.my_weixun_10.bean.MoviesBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 濠 on 2016/10/29.
 */

public interface MoviesURL {
    @GET("video")
    Call<MoviesBean> getString(@Query("dtype") String dtype, @Query("q") String q, @Query("key") String key);
}
