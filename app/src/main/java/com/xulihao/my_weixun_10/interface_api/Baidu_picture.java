package com.xulihao.my_weixun_10.interface_api;

import com.xulihao.my_weixun_10.bean.Baidu_Picture_Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 濠 on 2016/11/14.
 *
 */
public interface Baidu_picture {
    @GET("listjson")
    Call<Baidu_Picture_Bean>getString(@Query("pn" )String pn,@Query("rn") String rn,
                                      @Query("tag1") String tag1,@Query("tag2") String tag2,
                                      @Query("ie") String ie);
}
