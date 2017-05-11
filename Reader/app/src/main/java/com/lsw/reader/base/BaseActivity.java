package com.lsw.reader.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.lsw.reader.R;
import com.lsw.reader.utils.StatusBarCompat;

import butterknife.ButterKnife;

/**
 * Created by Luosiwei on 2017/5/10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    // 状态栏颜色
    protected int statusBarColor = 0;
    // 状态栏布局view
    protected View statusView;
    //  上下文
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if(statusBarColor == 0){
          statusView = StatusBarCompat.compact(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else if(statusBarColor != -1){
            statusView = StatusBarCompat.compact(this,statusBarColor);
        }
        transparent19and20();
        mContext=this;
        ButterKnife.bind(this);

    }

    /**
     * todo:
     * question: this why set build version control
     * answer:
     * 设置沉浸式标题栏
     */
    public void transparent19and20(){
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP&&Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initDatas();

    /**
     *  初始化布局
     */
    public abstract void initViews();
}
