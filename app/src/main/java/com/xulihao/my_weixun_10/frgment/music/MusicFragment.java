package com.xulihao.my_weixun_10.frgment.music;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.adapter.MusicTopTabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 濠 on 2016/11/13.
 */
public abstract class MusicFragment extends android.support.v4.app.Fragment {
    private TabLayout mActivity_tabc;
    private ViewPager mActivity_viewPager;
    private List<String> mMusic_tabs_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_item_music, null, false);
        mActivity_tabc = (TabLayout) view.findViewById(R.id.music_activity_tabs);
        mActivity_viewPager = (ViewPager) view.findViewById(R.id.music_activity_viewPager);
        initData();
        return view;
    }

    private void initData() {
        mMusic_tabs_title = new ArrayList<>();
        String[] array = getActivity().getResources().getStringArray(getTabTitle());
        for (String s:array) {
            mMusic_tabs_title.add(s);
        }
        MusicTopTabPagerAdapter musicTopTabPagerAdapter = new MusicTopTabPagerAdapter(this.getActivity().getSupportFragmentManager(), mMusic_tabs_title);
        mActivity_viewPager.setOffscreenPageLimit(9);
        mActivity_viewPager.setAdapter(musicTopTabPagerAdapter);
        mActivity_tabc.setupWithViewPager(mActivity_viewPager);
    }
    public abstract int getTabTitle();
}
