package com.xulihao.my_weixun_10.factory;


import android.support.v4.app.Fragment;

import com.xulihao.my_weixun_10.frgment.TopMessageFragment;
import com.xulihao.my_weixun_10.frgment.TopMoviesFragment;
import com.xulihao.my_weixun_10.frgment.TopMusicFragment;
import com.xulihao.my_weixun_10.frgment.TopNewsFragment;

/**
 * Created by 濠 on 2016/11/13.
 */

public class TopTabFragmentFactory {
    public static final int FRAGMENT_MUSIC = 0;
    public static final int FRAGMENT_NEWS= 1;
    public static final int FRAGMENT_MOVIES = 2;
    public static final int FRAGMENT_MESSAGE= 3;

    public static Fragment createTopTabFragmentFactory(int position){
        Fragment fragment = null;
        switch (position){

            case FRAGMENT_MUSIC:
                fragment = new TopMusicFragment();
                break;
            case FRAGMENT_NEWS:
                fragment = new TopNewsFragment();
                break;
            case FRAGMENT_MOVIES:
                fragment = new TopMoviesFragment();
                break;
            case FRAGMENT_MESSAGE:
                fragment = new TopMessageFragment();
                break;
            default:
                break;

        }

        return fragment;
    }

}
