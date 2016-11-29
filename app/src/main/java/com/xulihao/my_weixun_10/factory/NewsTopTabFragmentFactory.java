package com.xulihao.my_weixun_10.factory;


import android.support.v4.app.Fragment;

import com.xulihao.my_weixun_10.frgment.news.TopCaijingFragment;
import com.xulihao.my_weixun_10.frgment.news.TopGuojiFragment;
import com.xulihao.my_weixun_10.frgment.news.TopGuoneiFragment;
import com.xulihao.my_weixun_10.frgment.news.TopJunshiFragment;
import com.xulihao.my_weixun_10.frgment.news.TopKejiFragment;
import com.xulihao.my_weixun_10.frgment.news.TopShehuiFragment;
import com.xulihao.my_weixun_10.frgment.news.TopShishangFragment;
import com.xulihao.my_weixun_10.frgment.news.TopTiyuFragment;
import com.xulihao.my_weixun_10.frgment.news.TopTopFragment;
import com.xulihao.my_weixun_10.frgment.news.TopYuleFragment;

/**
 * Created by 濠 on 2016/11/13.
 */

public class NewsTopTabFragmentFactory {
    public static final int FRAGMENT_Top = 0;
    public static final int FRAGMENT_SHEHUI = 1;
    public static final int FRAGMENT_GUONEI = 2;
    public static final int FRAGMENT_GUOJI = 3;
    public static final int FRAGMENT_YULE = 4;
    public static final int FRAGMENT_TIYU = 5;
    public static final int FRAGMENT_JUNSHI = 6;
    public static final int FRAGMENT_KEJI = 7;
    public static final int FRAGMENT_CAIJING = 8;
    public static final int FRAGMENT_SHISHANG = 9;


    public static Fragment createTopTabFragmentFactory(int position){
        Fragment fragment = null;
        switch (position){
            case FRAGMENT_Top:
                fragment = new TopTopFragment();
                break;
            case FRAGMENT_SHEHUI:
                fragment = new TopShehuiFragment();
                break;
            case FRAGMENT_GUONEI:
                fragment = new TopGuoneiFragment();
                break;

            case FRAGMENT_GUOJI:
                fragment = new TopGuojiFragment();
                break;
            case FRAGMENT_YULE:
                fragment = new TopYuleFragment();
                break;
            case FRAGMENT_TIYU:
                fragment = new TopTiyuFragment();
                break;
            case FRAGMENT_JUNSHI:
                fragment = new TopJunshiFragment();
                break;
            case FRAGMENT_KEJI:
                fragment = new TopKejiFragment();
                break;
            case FRAGMENT_CAIJING:
                fragment = new TopCaijingFragment();
                break;
            case FRAGMENT_SHISHANG:
                fragment = new TopShishangFragment();
                break;

            default:
                break;

        }
        return fragment;
    }

}
