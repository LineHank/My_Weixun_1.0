package com.xulihao.my_weixun_10.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xulihao.my_weixun_10.R;

import java.util.ArrayList;

public class Activity_Movies_Content_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__movies__content_item);
        TextView tv_movies_content_item = (TextView) findViewById(R.id.tv_movies_content_item);
        Intent intent = getIntent();
        ArrayList<String> playlinks = intent.getStringArrayListExtra("playlinks");
        tv_movies_content_item.setText(playlinks.toString());
    }
}
