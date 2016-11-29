package com.xulihao.my_weixun_10.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import com.xulihao.my_weixun_10.factory.TopTabFragmentFactory;

/**
 * Created by 濠 on 2016/11/13.
 */

public class TopTabPagerAdapter extends FragmentPagerAdapter {
    RadioGroup mRadioGroup;
    public TopTabPagerAdapter(FragmentManager fm, RadioGroup radioGroup) {
        super(fm);
        mRadioGroup = radioGroup;
    }

    @Override
    public Fragment getItem(int position) {
        return TopTabFragmentFactory.createTopTabFragmentFactory(position);
    }

    @Override
    public int getCount() {
        return mRadioGroup.getChildCount();
    }
}
