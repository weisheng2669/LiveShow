package com.wangchao.mywork.utils;

import android.content.Context;
import android.util.Log;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler mCrashHandler = new CrashHandler();
    private Context mContext;
    private static Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private CrashHandler(){

    }
    public static CrashHandler getInstance(){
        return mCrashHandler;
    }
    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        ex.printStackTrace();
    }
}
