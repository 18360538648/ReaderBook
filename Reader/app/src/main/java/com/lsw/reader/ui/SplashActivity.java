package com.lsw.reader.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    @BindView(R.id.tv_skip_time)
    TextView tvSkipTime;
    public String TAG="lsw_SplashActivity";
    // 定时器handle
    private Handler mHandler = new Handler();
    // 倒计时器
    private MyCountDownTimer myCountDownTimer =new MyCountDownTimer(4000,1000);
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
        myCountDownTimer.start();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                goHome();
//            }
//        },3000);

    }

    /*
     *跳转到首页
     */
    public void goHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture 总倒计时时间
         * @param countDownInterval 每一次倒计时时间间隔
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        //l为剩余的时间
        @Override
        public void onTick(long l) {
            tvSkipTime.setText("倒计时"+l/1000);
        }
        // 当时间倒计时完,调用此方法
        @Override
        public void onFinish() {
            goHome();
        }
    }
}
