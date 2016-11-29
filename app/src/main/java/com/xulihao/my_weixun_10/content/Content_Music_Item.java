package com.xulihao.my_weixun_10.content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.bean.Music_Content_Bean;
import com.xulihao.my_weixun_10.frgment.TopMusicFragment;
import com.xulihao.my_weixun_10.interface_api.Music_Baidu_Content;
import com.xulihao.my_weixun_10.player.Player;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.xulihao.my_weixun_10.R.id.music_image;

public class Content_Music_Item extends Activity {

    private ImageButton mTitle_bar_back;
    private View mMusic_pager_loading;
    private ImageView mMusic_image;
    private TextView mMusic_name;
    private TextView mMusic_style;
    private TextView mMusic_author;
    private TextView mMusic_country;
    private RatingBar mMusic_ratingbar;
    private TextView mMusic_hot;
    private Intent mIntent;
    private String mMusic_image1;
    private String mMusic_name1;
    private String mMusic_style1;
    private String mMusic_author1;
    private String mMusic_country1;
    private String mMusic_hot1;
    private Button btnPause, btnPlayUrl, btnStop;
    private SeekBar skbProgress;
    private Player player;
    private String mSong_id1;
    private Retrofit mRetrofit;
    private Call<Music_Content_Bean> mCall;
    private Music_Content_Bean mBean;
    private String mShow_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content__music__item);
        initfind();
        mMusic_pager_loading.setVisibility(View.GONE);
        initData();
    }

    private void initData() {
        Glide.with(getApplication()).load(mMusic_image1).into(mMusic_image);
        mMusic_name.setText(mMusic_name1);
        mMusic_style.setText(mMusic_style1);
        mMusic_author.setText(mMusic_author1);
        mMusic_country.setText(mMusic_country1);
        mMusic_ratingbar.setProgress(Integer.parseInt(mMusic_hot1)/1000);
        mMusic_hot.setText(mMusic_hot1+"人听");
        mTitle_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TopMusicFragment.class);
                startActivity(intent);
            }
        });
    }

    private void initfind() {
        mTitle_bar_back = (ImageButton) findViewById(R.id.title_bar_back);
        mMusic_pager_loading = (View) findViewById(R.id.music_pager_loading);
        mMusic_image = (ImageView) findViewById(music_image);
        mMusic_name = (TextView) findViewById(R.id.music_name);
        mMusic_style = (TextView) findViewById(R.id.music_style);
        mMusic_author = (TextView) findViewById(R.id.music_author);
        mMusic_country = (TextView) findViewById(R.id.music_country);
        mMusic_ratingbar = (RatingBar) findViewById(R.id.music_ratingbar);
        mMusic_hot = (TextView) findViewById(R.id.music_hot);
        mIntent = getIntent();
        mMusic_image1 = mIntent.getStringExtra("music_image");
        mMusic_name1 = mIntent.getStringExtra("music_name");
        mMusic_style1 = mIntent.getStringExtra("music_style");
        mMusic_author1 = mIntent.getStringExtra("music_author");
        mMusic_country1 = mIntent.getStringExtra("music_country");
        mMusic_hot1 = mIntent.getStringExtra("music_hot");
        mSong_id1 = mIntent.getStringExtra("song_id");
        btnPlayUrl = (Button) this.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new ClickEvent());
        btnPause = (Button) this.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new ClickEvent());
        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new ClickEvent());
        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(skbProgress);
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://tingapi.ting.baidu.com/v1/restserver/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Music_Baidu_Content music_baidu_content = mRetrofit.create(Music_Baidu_Content.class);
        mCall = music_baidu_content.getString("webapp_music", "baidu.ting.song.playAAC", "json", "", mSong_id1);
        mCall.enqueue(new Callback<Music_Content_Bean>() {
            @Override
            public void onResponse(Call<Music_Content_Bean> call, Response<Music_Content_Bean> response) {
                mBean = response.body();
                mShow_link = mBean.getBitrate().getShow_link();
            }
            @Override
            public void onFailure(Call<Music_Content_Bean> call, Throwable t) {

            }
        });
    }
    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            if (arg0 == btnPause) {
                player.pause();
            } else if (arg0 == btnPlayUrl) {
                //在百度MP3里随便搜索到的,大家可以试试别的链接
                String url=mShow_link;
                player.playUrl(url);
            } else if (arg0 == btnStop) {
                player.stop();
            }
        }
    }
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }

}
