package com.lsw.reader.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.lsw.reader.R;

/**
 * Created by Luosiwei on 2017/5/10.
 */
public class StatusBarCompat {
    private static int VALID_CODE = -1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static View compact(Activity activity, int statusBarColor) {
        int color = ContextCompat.getColor(activity, R.color.colorPrimaryDark);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (statusBarColor != VALID_CODE) {
                color = statusBarColor;
            }
            activity.getWindow().setStatusBarColor(color);
            return null;
        }
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            ViewGroup contentview = (ViewGroup) activity.findViewById(android.R.id.content);
            View statusBarView = contentview.getChildAt(0);
            if (statusBarView != null && statusBarView.getHeight() == getStatusBarHeight(activity)) {
                statusBarView.setBackgroundColor(color);
                return statusBarView;
            }
            statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentview.addView(statusBarView,lp);
            return statusBarView;
        }
        return  null;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "demen", "android");
        if (resourceId != 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}
