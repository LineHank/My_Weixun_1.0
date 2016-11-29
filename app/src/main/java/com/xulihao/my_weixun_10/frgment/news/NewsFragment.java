package com.xulihao.my_weixun_10.frgment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.adapter.NewsTopTabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 濠 on 2016/11/13.
 */
public abstract class NewsFragment extends android.support.v4.app.Fragment{

    private TabLayout mActivity_tabc;
    private ViewPager mActivity_viewPager;
    private List<String> news_tabs_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_item, null, false);
        mActivity_tabc = (TabLayout) view.findViewById(R.id.activity_tabs);
        mActivity_viewPager = (ViewPager) view.findViewById(R.id.activity_viewPager);
        initData();
        return view;
    }

    private void initData() {
        news_tabs_title = new ArrayList<>();
        String[] array = getActivity().getResources().getStringArray(getTabTiele());
        for (String s:array) {
            news_tabs_title.add(s);
        }
        NewsTopTabPagerAdapter newsTopTabPagerAdapter = new NewsTopTabPagerAdapter(getActivity().getSupportFragmentManager(), news_tabs_title);
        mActivity_viewPager.setOffscreenPageLimit(10);
        mActivity_viewPager.setAdapter(newsTopTabPagerAdapter);
        mActivity_tabc.setupWithViewPager(mActivity_viewPager);
    }
    public abstract int getTabTiele();
}
