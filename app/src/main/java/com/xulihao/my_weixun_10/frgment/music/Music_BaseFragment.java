package com.xulihao.my_weixun_10.frgment.music;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.adapter.MusicAdapter;
import com.xulihao.my_weixun_10.bean.Music_Bean;
import com.xulihao.my_weixun_10.content.Content_Music_Item;
import com.xulihao.my_weixun_10.interface_api.Music_Baidu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 濠 on 2016/11/15.
 */

public abstract class Music_BaseFragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private View progressbar;
    private Music_Baidu mMusic_baidu;
    private SwipeRefreshLayout mTopRefresh;
    private Call<Music_Bean> mCall;
    private View mView;
    private Music_Bean mBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_swipe_fragment_base, container, false);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.my_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycleview);
        progressbar = mView.findViewById(R.id.progressbar);
        initData();
        return mView;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tingapi.ting.baidu.com/v1/restserver/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMusic_baidu = retrofit.create(Music_Baidu.class);
        mCall = mMusic_baidu.getString("webapp_music", "baidu.ting.billboard.billList", "json", "", getType(), "100", "0");
        mCall.enqueue(new Callback<Music_Bean>() {
            @Override
            public void onResponse(Call<Music_Bean> call, Response<Music_Bean> response) {
                progressbar.setVisibility(View.GONE);
                Music_BaseFragment.this.mBean = response.body();
                List<Music_Bean.SongListBean> data = Music_BaseFragment.this.mBean.getSong_list();
                MusicAdapter adapter = new MusicAdapter(data, getActivity());
                GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        String pic_big = mBean.getSong_list().get(position).getPic_big();
                        String album_title = mBean.getSong_list().get(position).getAlbum_title();
                        String style = mBean.getSong_list().get(position).getStyle();
                        String author = mBean.getSong_list().get(position).getAuthor();
                        String country = mBean.getSong_list().get(position).getCountry();
                        String hot = mBean.getSong_list().get(position).getHot();
                        String song_id = mBean.getSong_list().get(position).getSong_id();
                        init_content(pic_big,album_title,style,author,country,hot,song_id);
                    }

                    @Override
                    public void onLongClick(int position) {

                    }
                });
            }
            @Override
            public void onFailure(Call<Music_Bean> call, Throwable t) {

            }
        });
    }
    private void init_content(String music_image,String music_name ,String music_style ,String music_author
            ,String music_country ,String music_hot,String song_id ) {
        Intent intent = new Intent(getActivity(), Content_Music_Item.class);
        intent.putExtra("music_image", music_image);
        intent.putExtra("music_name", music_name);
        intent.putExtra("music_style", music_style);
        intent.putExtra("music_author", music_author);
        intent.putExtra("music_country", music_country);
        intent.putExtra("music_hot", music_hot);
        intent.putExtra("song_id", song_id);
        startActivity(intent);
    }
    public abstract String getType();
}
