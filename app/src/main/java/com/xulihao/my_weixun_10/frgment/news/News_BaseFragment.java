package com.xulihao.my_weixun_10.frgment.news;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.adapter.NewsAdapter;
import com.xulihao.my_weixun_10.bean.News_Juhe_Bean;
import com.xulihao.my_weixun_10.interface_api.News_Juhe;
import com.xulihao.my_weixun_10.webview.Activity_WebView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 濠 on 2016/10/29.
 */
public abstract class News_BaseFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private View mNews_progressbar;
    private Call<News_Juhe_Bean> mCall;
    private News_Juhe_Bean bean;
    private View mView;
    private News_Juhe mNewsURL;

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
        mNews_progressbar = mView.findViewById(R.id.progressbar);
        initData();
        return mView;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/toutiao/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mNewsURL = retrofit.create(News_Juhe.class);
        mCall = mNewsURL.getString(getType(), "9f9dfb6f819c3f7a616646bf98724f2e");
        mCall.enqueue(new Callback<News_Juhe_Bean>() {
            @Override
            public void onResponse(Call<News_Juhe_Bean> call, Response<News_Juhe_Bean> response) {
                mNews_progressbar.setVisibility(View.GONE);
                News_BaseFragment.this.bean =response.body();
//                final List<News_Juhe_Bean.ResultBean.DataBean> data = News_BaseFragment.this.bean.getResult().getData();
                NewsAdapter adapter = new NewsAdapter(News_BaseFragment.this.bean.getResult().getData(), getActivity());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        String weburl = bean.getResult().getData().get(position).getUrl();
                        init_webview(weburl);
                    }

                    @Override
                    public void onLongClick(int position) {

                    }
                });
            }

            @Override
            public void onFailure(Call<News_Juhe_Bean> call, Throwable t) {
                Log.i("ljd", "onResponse: " + t.getMessage());


            }
        });
    }
    private void init_webview(String weburl) {
        Intent intent = new Intent(getActivity(), Activity_WebView.class);
        intent.putExtra("weburl", weburl);
        startActivity(intent);
    }

    public abstract String getType();
}
