package com.cheung.android.base.baseuiframe.interfaces;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: 崩溃日志接口协议
 */
public interface ICrashProcess {
    void onException(Thread thread, Throwable exception) throws Exception;
}
