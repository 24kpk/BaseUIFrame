package com.cheung.android.demo.baseuiframe;

import android.app.Application;

import com.cheung.android.base.baseuiframe.BasicConfig;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description:
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BasicConfig.getInstance(this)
                .initDir("BaseUIFrame") // or initDir(rootDirName)
                .initExceptionHandler()
                .initLog(true);
    }
}
