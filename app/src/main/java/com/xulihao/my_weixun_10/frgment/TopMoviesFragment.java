package com.xulihao.my_weixun_10.frgment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.bean.MoviesBean;
import com.xulihao.my_weixun_10.interface_api.MoviesURL;
import com.xulihao.my_weixun_10.webview.Activity_WebView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 濠 on 2016/11/13.
 */
public class TopMoviesFragment extends Fragment implements View.OnClickListener {

    private TextView mTv_movies_title;
    private TextView mTv_movies_tag;
    private TextView mTv_movies_time;
    private TextView mTv_movies_act;
    private TextView mTv_movies_area;
    private TextView mTv_movies_desc;
    private EditText mEt_movies;
    private String mMovies;
    private ImageView mIv_movies_cover;
    private ImageView mIv_movies_sousuo;
    private Button mBtn_movies_sp;
    private MoviesBean mBean;
    private View mView;
    private RadioButton mRb_movies_sp;
    //    private ListView mLv_movies;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_movies_layout, null, false);
        initFind();
        mIv_movies_sousuo.setOnClickListener(this);
        return mView;
    }
    private void initBtn(String mlist) {
        Intent intent = new Intent(getActivity(), Activity_WebView.class);
        intent.putExtra("weburl", mlist);
        startActivity(intent);
    }


    private void initFind() {
        mTv_movies_title = (TextView) mView.findViewById(R.id.tv_movies_title);
        mTv_movies_tag = (TextView) mView.findViewById(R.id.tv_movies_tag);
        mTv_movies_time = (TextView) mView.findViewById(R.id.tv_movies_time);
        mTv_movies_act = (TextView) mView.findViewById(R.id.tv_movies_act);
        mTv_movies_area = (TextView) mView.findViewById(R.id.tv_movies_area);
        mTv_movies_desc = (TextView) mView.findViewById(R.id.tv_movies_desc);
        mIv_movies_cover = (ImageView) mView.findViewById(R.id.iv_movies_cover);
        mIv_movies_sousuo = (ImageView) mView.findViewById(R.id.iv_movies_sousuo);
        mEt_movies = (EditText) mView.findViewById(R.id.et_movies);
//        mBtn_movies_sp = (Button) mView.findViewById(R.id.btn_movies_sp);
//        mLv_movies = (ListView) mView.findViewById(R.id.lv_movies);
        mRb_movies_sp = (RadioButton) mView.findViewById(R.id.rb_movies_sp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_movies_sousuo:
                mMovies = mEt_movies.getText().toString().trim();
                if (TextUtils.isEmpty(mMovies)) {
                    Toast.makeText(getActivity(), "请输入影视资源名称", Toast.LENGTH_SHORT).show();
                } else {
                    initData();
                }
                break;
            default:
                break;
        }
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/onebox/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MoviesURL moviesURL = retrofit.create(MoviesURL.class);
        Call<MoviesBean> call = moviesURL.getString("", mMovies, "f04121691e6e86def1f0ae3eebd2c356");
        call.enqueue(new Callback<MoviesBean>() {
            @Override
            public void onResponse(final Call<MoviesBean> call, final Response<MoviesBean> response) {
                Log.i("xlh", "onResponse: " + response.toString());
                mBean = response.body();
                if (mBean.getResult() == null) {
                    Toast.makeText(getActivity(), "查询不到该影片相关信息", Toast.LENGTH_SHORT).show();
                } else {
                    mTv_movies_title.setText("影视名称:" + mBean.getResult().getTitle());
                    mTv_movies_time.setText("上映时间:" + mBean.getResult().getYear());
                    mTv_movies_tag.setText("类型:" + mBean.getResult().getTag());
                    mTv_movies_act.setText("演员:" + mBean.getResult().getAct());
                    mTv_movies_area.setText(mBean.getResult().getArea());
                    mTv_movies_desc.setText(mBean.getResult().getDesc());
                    Glide.with(getActivity()).load(mBean.getResult().getCover()).into(mIv_movies_cover);
                    Toast.makeText(getActivity(), "影视资源搜索完毕", Toast.LENGTH_SHORT).show();
                    Log.i("xlh", "Playlinks" + mBean.getResult().getPlaylinks());
                    final MoviesBean.ResultBean.PlaylinksBean playlinks = mBean.getResult().getPlaylinks();

                    mRb_movies_sp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                          initBtn(mBean.getResult().getPlaylinks().getSohu());

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("请选择你想进入的视频网站");
                            final String[] items = {"pptv","乐视","爱奇艺","腾讯视频","搜狐视频","土豆","优酷","芒果TV","56","暴风"};
                            builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String item = items[which];
                                    if (item=="pptv"){
                                        if (mBean.getResult().getPlaylinks().getPptv()!=null){
                                            String pptv =   mBean.getResult().getPlaylinks().getPptv();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(pptv);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="乐视"){
                                        if (mBean.getResult().getPlaylinks().getLeshi()!=null){
                                            String leshi = mBean.getResult().getPlaylinks().getLeshi();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(leshi);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="爱奇艺"){
                                        if (mBean.getResult().getPlaylinks().getQiyi()!=null){
                                            String qiyi = mBean.getResult().getPlaylinks().getQiyi();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(qiyi);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="腾讯视频"){
                                        if (mBean.getResult().getPlaylinks().getQq()!=null){
                                            String qq = mBean.getResult().getPlaylinks().getQq();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(qq);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="搜狐"){
                                        if (mBean.getResult().getPlaylinks().getSohu()!=null){
                                            String sohu = mBean.getResult().getPlaylinks().getSohu();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(sohu);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="土豆"){
                                        if (mBean.getResult().getPlaylinks().getTudou()!=null){
                                            String tudou = mBean.getResult().getPlaylinks().getTudou();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(tudou);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="优酷"){
                                        if (mBean.getResult().getPlaylinks().getYouku()!=null){
                                            String youku = mBean.getResult().getPlaylinks().getYouku();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(youku);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="芒果TV"){
                                        if (mBean.getResult().getPlaylinks().getImgo()!=null){
                                            String imgo = mBean.getResult().getPlaylinks().getImgo();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(imgo);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="56"){
                                        if (mBean.getResult().getPlaylinks().getValue56com()!=null){
                                            String five = mBean.getResult().getPlaylinks().getValue56com();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(five);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(item=="暴风"){
                                        if (mBean.getResult().getPlaylinks().getBaofeng()!=null){
                                            String baofeng = mBean.getResult().getPlaylinks().getBaofeng();
                                            Toast.makeText(getActivity(),"有此资源正在转载......",Toast.LENGTH_SHORT).show();
                                            initBtn(baofeng);
                                        }else {
                                            Toast.makeText(getActivity(),"没有此视频资源",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    dialog.dismiss();
                                }
                            });

                            builder.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MoviesBean> call, Throwable t) {

            }
        });
    }

}
