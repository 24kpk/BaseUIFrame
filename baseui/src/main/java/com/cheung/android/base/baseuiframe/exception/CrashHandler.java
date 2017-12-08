package com.cheung.android.base.baseuiframe.exception;

import com.cheung.android.base.baseuiframe.interfaces.ICrashProcess;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: 异常信息收集
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler sInstance;
    private ICrashProcess mCrashProcess;

    private CrashHandler(ICrashProcess crashProcessImpl) {
        // 将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mCrashProcess = crashProcessImpl;
    }

    public synchronized static CrashHandler getInstance(ICrashProcess crashProcessImpl) {
        if (sInstance == null) {
            sInstance = new CrashHandler(crashProcessImpl);
        }
        return sInstance;
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用uncaughtException方法
     * thread为出现未捕获异常的线程，
     * exception为未捕获的异常。
     */
    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        try {
            mCrashProcess.onException(thread, exception);
        } catch (Exception e) {
        } finally {
            System.exit(0);
        }
    }
}
