package com.xulihao.my_weixun_10.factory;


import android.support.v4.app.Fragment;

import com.xulihao.my_weixun_10.frgment.music.TopBalladFragment;
import com.xulihao.my_weixun_10.frgment.music.TopClassicsFragment;
import com.xulihao.my_weixun_10.frgment.music.TopEuropeFragment;
import com.xulihao.my_weixun_10.frgment.music.TopHotFragment;
import com.xulihao.my_weixun_10.frgment.music.TopInternetFragment;
import com.xulihao.my_weixun_10.frgment.music.TopJazzFragment;
import com.xulihao.my_weixun_10.frgment.music.TopNewFragment;
import com.xulihao.my_weixun_10.frgment.music.TopPopularFragment;
import com.xulihao.my_weixun_10.frgment.music.TopRockFragment;
import com.xulihao.my_weixun_10.frgment.music.TopTelevisionFragment;

/**
 * Created by 濠 on 2016/11/13.
 */

public class MusicTopTabFragmentFactory {
    public static final int FRAGMENT_NEW = 0;
    public static final int FRAGMENT_HOT = 1;
    public static final int FRAGMENT_ROCK = 2;
    public static final int FRAGMENT_JAZZ = 3;
    public static final int FRAGMENT_POPULAR = 4;
    public static final int FRAGMENT_EUROPE = 5;
    public static final int FRAGMENT_CLASSICS = 6;
    public static final int FRAGMENT_BALLAD = 7;
    public static final int FRAGMENT_TELEVISION = 8;
    public static final int FRAGMENT_INTERNET = 9;


    public static Fragment createTopTabFragmentFactory(int position){
        Fragment fragment = null;
        switch (position){
            case FRAGMENT_NEW:
                fragment = new TopNewFragment();
                break;
            case FRAGMENT_HOT:
                fragment = new TopHotFragment();
                break;
            case FRAGMENT_ROCK:
                fragment = new TopRockFragment();
                break;
            case FRAGMENT_JAZZ:
                fragment = new TopJazzFragment();
                break;
            case FRAGMENT_POPULAR:
                fragment = new TopPopularFragment();
                break;
            case FRAGMENT_EUROPE:
                fragment = new TopEuropeFragment();
                break;
            case FRAGMENT_CLASSICS:
                fragment = new TopClassicsFragment();
                break;
            case FRAGMENT_BALLAD:
                fragment = new TopBalladFragment();
                break;
            case FRAGMENT_TELEVISION:
                fragment = new TopTelevisionFragment();
                break;
            case FRAGMENT_INTERNET:
                fragment = new TopInternetFragment();
                break;

            default:
                break;

        }
        return fragment;
    }

}
