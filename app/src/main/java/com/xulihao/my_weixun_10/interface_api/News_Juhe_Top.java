package com.xulihao.my_weixun_10.interface_api;


import com.xulihao.my_weixun_10.bean.News_Juhe_Top_Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 濠 on 2016/11/12.
 */

public interface News_Juhe_Top {
    @GET("index")
    Call<News_Juhe_Top_Bean> getString(@Query("type") String type, @Query("key") String key);
}
