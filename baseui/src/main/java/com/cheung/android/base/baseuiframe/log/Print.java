package com.cheung.android.base.baseuiframe.log;

import com.orhanobut.logger.Logger;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: Log输出类
 */
public class Print {
    public static void json(String json) {
        Logger.json(json);}

    public static void e(Object log) {
        Logger.e(String.valueOf(log));
    }

    public static void d(Object log) {
        Logger.d(String.valueOf(log));
    }

    public static void i(Object log) {
        Logger.i(String.valueOf(log));
    }

    public static void v(Object log) {
        Logger.v(String.valueOf(log));
    }

    public static void w(Object log) {Logger.w(String.valueOf(log));}

    public static void e(Object log, Throwable tr) {
        Logger.e(String.valueOf(log), tr);
    }

    public static void d(Object log, Throwable tr) {
        Logger.d(String.valueOf(log), tr);
    }

    public static void i(Object log, Throwable tr) {
        Logger.i(String.valueOf(log), tr);
    }

    public static void v(Object log, Throwable tr) {
        Logger.v(String.valueOf(log), tr);
    }

    public static void w(Object log, Throwable tr) {
        Logger.w(String.valueOf(log), tr);
    }
}
