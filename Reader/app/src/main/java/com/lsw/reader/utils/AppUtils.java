package com.lsw.reader.utils;

import android.content.Context;

/**
 * Created by Luosiwei on 2017/5/11.
 */
public class AppUtils {
    // 上下文
    public static Context mContext;
    //UI 线程
    public static Thread uiThread;

    /**
     * 得到Appliction对象和UI线程
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
        uiThread = Thread.currentThread();
    }
}
