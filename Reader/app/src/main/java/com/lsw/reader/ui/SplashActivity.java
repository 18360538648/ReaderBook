package com.lsw.reader.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.lsw.reader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luosiwei on 2017/3/10.
 * 闪屏页面
 */
public class SplashActivity extends Activity {
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                goHome();
            }
        }, 3000);
    }

    /*
     *跳转到首页
     */
    public void goHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
