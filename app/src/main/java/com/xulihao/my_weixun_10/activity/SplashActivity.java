package com.xulihao.my_weixun_10.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.bean.Baidu_Picture_Bean;
import com.xulihao.my_weixun_10.interface_api.Baidu_picture;
import com.xulihao.my_weixun_10.utils.GlideUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends Activity {
    String url = "http://www.dujin.org/sys/bing/1366.php";
    private Timer mTimer;
    private ImageView mIv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://image.baidu.com/channel/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Baidu_picture badidu_picture = retrofit.create(Baidu_picture.class);
        Call<Baidu_Picture_Bean> call = badidu_picture.getString("0","30","明星","全部","utf8");
        call.enqueue(new Callback<Baidu_Picture_Bean>() {
            @Override
            public void onResponse(Call<Baidu_Picture_Bean> call, Response<Baidu_Picture_Bean> response) {
                Baidu_Picture_Bean bean = response.body();
                mIv_splash = (ImageView) findViewById(R.id.iv_splash);
         //       Glide.with(getApplication()).load(bean.getResult().getData().get(0).getThumbnail_pic_s()).into(mIv_splash);
                GlideUtils.loadImageViewAnim(SplashActivity.this, bean.getData().get(3).getDownload_url(), R.anim.zoomin, mIv_splash);
            }

            @Override
            public void onFailure(Call<Baidu_Picture_Bean> call, Throwable t) {

            }
        });

        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mTimer.schedule(timerTask, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
