package com.xulihao.my_weixun_10.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xulihao.my_weixun_10.R;
import com.xulihao.my_weixun_10.adapter.TopTabPagerAdapter;
import com.xulihao.my_weixun_10.frgment.NoScrollViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private RadioButton mTop_rb_music;
    private RadioButton mTop_rb_movies;
    private RadioButton mTop_rb_news;
    private RadioButton mTop_rb_message;
    private RadioGroup mTop_radiogroup;
    private AppBarLayout mAppbarlayout;
    private NoScrollViewPager mViewpager;
    private DrawerLayout mDrawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
        initView();
    }

    private void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTop_radiogroup = (RadioGroup) findViewById(R.id.top_radiogroup);
        mTop_rb_music = (RadioButton) findViewById(R.id.top_rb_music);
        mTop_rb_movies = (RadioButton) findViewById(R.id.top_rb_movies);
        mTop_rb_news = (RadioButton) findViewById(R.id.top_rb_news);
        mTop_rb_message = (RadioButton) findViewById(R.id.top_rb_message);
        mAppbarlayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        mViewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        mDrawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void initData() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        TopTabPagerAdapter topTabPagerAdapter = new TopTabPagerAdapter(fragmentManager, mTop_radiogroup);
        mViewpager.setOffscreenPageLimit(4);
        mViewpager.setAdapter(topTabPagerAdapter);
        mTop_radiogroup.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.top_rb_music:
                mViewpager.setCurrentItem(0);
                break;
            case R.id.top_rb_news:
                mViewpager.setCurrentItem(1);
                break;
            case R.id.top_rb_movies:
                mViewpager.setCurrentItem(2);
                break;
            case R.id.top_rb_message:
                mViewpager.setCurrentItem(3);
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByDoubleClick();      //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitByDoubleClick() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_menu_home:
                item.isChecked();
                break;
            case R.id.nav_menu_feedback:

                break;
            case R.id.nav_menu_setting:

                break;
            case R.id.nav_menu_about:

                break;
            case R.id.nav_menu_login:

                break;
        }
        mDrawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }


}
