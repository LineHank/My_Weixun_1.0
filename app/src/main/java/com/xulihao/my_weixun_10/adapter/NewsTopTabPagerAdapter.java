package com.xulihao.my_weixun_10.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.xulihao.my_weixun_10.factory.NewsTopTabFragmentFactory;

import java.util.List;

/**
 * Created by 濠 on 2016/11/13.
 */

public class NewsTopTabPagerAdapter extends FragmentPagerAdapter {
    List<String> news_tabs_title;
    public NewsTopTabPagerAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        news_tabs_title=list;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsTopTabFragmentFactory.createTopTabFragmentFactory(position);
    }

    @Override
    public int getCount() {
        return news_tabs_title.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return news_tabs_title.get(position);
    }
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
