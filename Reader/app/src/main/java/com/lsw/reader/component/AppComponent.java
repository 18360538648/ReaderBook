package com.lsw.reader.component;

import android.content.Context;

import com.lsw.reader.module.AppModule;

import dagger.Component;

/**
 * Created by Luosiwei on 2017/5/11.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();
}
