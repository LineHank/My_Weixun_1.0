package com.xulihao.my_weixun_10.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xulihao.my_weixun_10.R;


/**
 * Created by 濠 on 2016/10/30.
 */

public class Activity_WebView extends Activity {

    private WebView wv_content;
    private String mWeburl;
    private ProgressBar mMPbLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_content);
        wv_content = (WebView) findViewById(R.id.wv_content);
        mMPbLoad = (ProgressBar) findViewById(R.id.mprogressvar);
        initData();
    }

    private void initData() {
        wv_content.setVisibility(View.GONE);
        mMPbLoad.setVisibility(View.GONE);
        Intent intent = getIntent();
        mWeburl = intent.getStringExtra("weburl");
        wv_content.loadUrl(mWeburl);
        wv_content.setWebViewClient(new diy());
        wv_content.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress) {
                mMPbLoad.setProgress(newProgress);
            }
        });
        wv_content.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mMPbLoad.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            public void onPageFinished(WebView view, String url) {
                mMPbLoad.setVisibility(View.GONE);
                wv_content.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });


    }

    protected class diy extends  WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
           view.loadUrl(mWeburl);
            return true;
        }
    }
}
