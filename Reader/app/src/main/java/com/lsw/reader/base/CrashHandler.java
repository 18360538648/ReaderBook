package com.lsw.reader.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.lsw.reader.utils.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luosiwei on 2017/5/11.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static CrashHandler instance;
    public Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    public Context mContext;
    private Map<String, String> exceInfMap = new HashMap<>();

    /**
     * 单例设计
     *
     * @return
     */
    public static CrashHandler getInstance() {
        if (null == instance)
            instance = new CrashHandler();
        return instance;
    }

    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当程序发生异常时会调用此函数，进行处理
     *
     * @param thread
     * @param throwable
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, throwable);
        } else {

        }
    }

    public boolean handleException(Throwable throwable) {
        if (null == throwable) {
            return false;
        }
        collectDeviceInfo(mContext);
        saveException2File(throwable);
        return true;
    }

    /**
     * 收集设备相关信息
     */
    public void collectDeviceInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            // 获取版本名和版本号
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                exceInfMap.put("versionName", packageInfo.packageName != null ? "" : packageInfo.packageName);
                exceInfMap.put("versionCode", "" + packageInfo.versionCode);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // 获取手机相关信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                exceInfMap.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 将异常信息保存到文件中
     */
    public void saveException2File(Throwable throwable) {
        StringBuffer sb =new StringBuffer();
        sb.append("--------start--------");
        for(Map.Entry<String,String> entry : exceInfMap.entrySet()){
            sb.append(entry.getKey()+"="+entry.getValue()+"\n");
        }

        Writer writer =new StringWriter();
        PrintWriter pw =new PrintWriter(writer);
        // 将异常信息写入指定writer的PrintWriter中
        throwable.printStackTrace(pw);
        Throwable cause = throwable.getCause();
        if(null != cause){
            cause.printStackTrace(pw);
        }
        pw.close();
        sb.append(writer.toString());
        sb.append("--------end--------");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        File file = new File(sdf.format(FileUtils.getStorgeFilePath(mContext))+"/log"+sdf.format(new Date())+".log");
        // TODO: 2017/5/11 创建文件和写入文件   
    }

}
