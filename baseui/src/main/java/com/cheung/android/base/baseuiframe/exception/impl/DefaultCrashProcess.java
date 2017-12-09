package com.cheung.android.base.baseuiframe.exception.impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.cheung.android.base.baseuiframe.interfaces.ICrashProcess;
import com.cheung.android.base.baseuiframe.utils.DateUtil;
import com.cheung.android.base.baseuiframe.utils.SDcardUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: 默认崩溃日志处理
 */
public class DefaultCrashProcess implements ICrashProcess {
    private static final String LOG_NAME_PREFIX = "crash_";
    private static final String LOG_NAME_SUFFIX = ".log";
    private Context mContext;

    public DefaultCrashProcess(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public void onException(Thread thread, Throwable exception) throws Exception {
        final StringBuilder sb = new StringBuilder(LOG_NAME_PREFIX).append(
                new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())))
                .append(LOG_NAME_SUFFIX);
        final File file = new File(SDcardUtil.getLogDirPath(), sb.toString());
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        // 导出手机信息
        savePhoneInfo(pw);
        // 导出发生异常的时间
        pw.println(DateUtil.formatDate(DateUtil.FORMAT, System.currentTimeMillis()));
        pw.println();
        // 导出异常的调用栈信息
        exception.printStackTrace(pw);
        pw.println();
        pw.close();
    }

    /**
     * 获取设备信息
     * @param pw
     * @throws Exception
     */
    private void savePhoneInfo(PrintWriter pw) throws Exception {
        // 应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi =
                pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.println("App信息：");
        pw.println("------------------------------------");
        pw.print("App 版本: ");
        pw.println(pi.versionName);
        pw.print("App Code: ");
        pw.println(pi.versionCode);
        pw.println("------------------------------------");
        pw.println("设备信息：");
        pw.println("------------------------------------");
        // android版本号
        pw.print("Android 版本号: ");
        pw.println(Build.VERSION.SDK_INT);
        pw.print("Android OS 版本名称: ");
        pw.println(Build.VERSION.RELEASE);

        // 手机制造商
        pw.print("设备制造商: ");
        pw.println(Build.MANUFACTURER);

        // 手机型号
        pw.print("设备型号: ");
        pw.println(Build.MODEL);

        // cpu架构
        pw.print("CPU架构-->CPU ABI: ");
        pw.println(Build.CPU_ABI);
        pw.println("------------------------------------");
        pw.println();
    }
}
