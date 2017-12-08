package com.cheung.android.base.baseuiframe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: Toast工具类
 */
public final class ToastUtil {
    public static Context context;

    private ToastUtil() {}

    public static void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


    public static void showToast(int resId) {
        Toast.makeText(context, context.getResources().getText(resId),
                Toast.LENGTH_SHORT).show();
    }


    public static void showLongToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    public static void showLongToast(int resId) {
        Toast.makeText(context, context.getResources().getText(resId),
                Toast.LENGTH_LONG).show();
    }
}
