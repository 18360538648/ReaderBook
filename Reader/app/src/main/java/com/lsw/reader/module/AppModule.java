package com.lsw.reader.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Luosiwei on 2017/5/11.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }
    @Provides
    public  Context provideContext(){
        return context;
    }
}
