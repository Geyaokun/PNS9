package com.punuo.sys.app.tools;

import android.app.Application;

/**
 * Author chzjy
 * Date 2016/12/19.
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());
    }
}
