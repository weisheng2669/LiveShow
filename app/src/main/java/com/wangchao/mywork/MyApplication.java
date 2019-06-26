package com.wangchao.mywork;

import android.app.Application;

import com.wangchao.mywork.utils.CrashHandler;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

    }
}
