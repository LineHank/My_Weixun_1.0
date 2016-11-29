package com.xulihao.my_weixun_10.frgment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.bean.Baidu_Picture_Bean;
import com.xulihao.my_weixun_10.interface_api.Baidu_picture;
import com.xulihao.my_weixun_10.utils.GlideUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 濠 on 2016/11/13.
 */
public class TopMessageFragment extends android.support.v4.app.Fragment {

    private LinearLayout mLinearLayout;
    private View mView;
    private Baidu_Picture_Bean mBean;
    int currentImg = 0;
    private List<Baidu_Picture_Bean.DataBean> mData;
    private ImageView mMImageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activiry_message, null, false);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.message_linearlayout);
        mMImageView = (ImageView) mView.findViewById(R.id.message_iv);
        initData();
        mMImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideUtils.loadImageViewAnim(getActivity(),mBean.getData().get(++currentImg).getDownload_url(), R.anim.zoomin, mMImageView);
            }
        });
        return mView;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://image.baidu.com/channel/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Baidu_picture badidu_picture = retrofit.create(Baidu_picture.class);
        Call<Baidu_Picture_Bean> call = badidu_picture.getString("0","3000","明星","全部","utf8");
        call.enqueue(new Callback<Baidu_Picture_Bean>() {
            @Override
            public void onResponse(Call<Baidu_Picture_Bean> call, Response<Baidu_Picture_Bean> response) {
                mBean = response.body();
                mData = mBean.getData();
                GlideUtils.loadImageViewAnim(getActivity(), mBean.getData().get(0).getDownload_url(), R.anim.zoomin, mMImageView);
            }

            @Override
            public void onFailure(Call<Baidu_Picture_Bean> call, Throwable t) {

            }
        });

//



















    }
}
