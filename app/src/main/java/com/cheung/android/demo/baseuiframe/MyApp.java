package com.cheung.android.demo.baseuiframe;

import android.app.Application;

import com.cheung.android.base.baseuiframe.BasicConfig;
import com.cheung.android.base.baseuiframe.log.Print;
import com.qmuiteam.qmui.util.QMUIColorHelper;
import com.qmuiteam.qmui.util.QMUIDeviceHelper;
import com.qmuiteam.qmui.util.QMUIDrawableHelper;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description:
 */
public class MyApp extends Application {
    public static final String INTENT_VALUE_TITLE_STR = MyApp.class.getSimpleName()+".intent.string.title";
    @Override
    public void onCreate() {
        super.onCreate();
        BasicConfig.getInstance(this)
                .initDir("BaseUIFrame") // or initDir(rootDirName)
                .initExceptionHandler()
                .initLog(true);

        Print.d("判断是否是平板设备:"+ QMUIDeviceHelper.isTablet(this));
        Print.d("判断是否Flyme系统:"+ QMUIDeviceHelper.isFlyme());
        Print.d("判断是否MIUI系统:"+ QMUIDeviceHelper.isMIUI());
        Print.d("判断是否魅族手机:"+ QMUIDeviceHelper.isMeizu());
        Print.d("判断是否小米手机:"+ QMUIDeviceHelper.isXiaomi());
    }
}
