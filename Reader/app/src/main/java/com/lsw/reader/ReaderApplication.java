package com.lsw.reader;

import android.app.Application;

import com.lsw.reader.base.CrashHandler;
import com.lsw.reader.component.AppComponent;
import com.lsw.reader.component.DaggerAppComponent;
import com.lsw.reader.module.AppModule;
import com.lsw.reader.utils.AppUtils;

/**
 * Created by Luosiwei on 2017/5/11.
 */
public class ReaderApplication extends Application{
    public ReaderApplication mInstance;
    public AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        mInstance = this;
        AppUtils.init(this);
        CrashHandler.getInstance().init(this);
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                       .appModule(new AppModule(this))
                       .build();
    }
}
