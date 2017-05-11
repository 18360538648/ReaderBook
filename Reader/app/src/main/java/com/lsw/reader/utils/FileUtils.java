package com.lsw.reader.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Luosiwei on 2017/5/11.
 */
public class FileUtils {

    public static String getStorgeFilePath(Context context) {
        String filePath = "";
        if (isSdcardAvaiable()) {
            filePath = context.getExternalCacheDir().getPath();
        } else {
            filePath = context.getCacheDir().getPath();
        }
        return filePath;
    }

    /**
     * 判断sd卡是否可用
     *
     * @return
     */
    private static boolean isSdcardAvaiable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
