package com.cheung.android.base.baseuiframe.interfaces;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: Activity协议
 */
public interface IActivity {
    int DESTROY = 0x00;
    int STOP    = 0x01;
    int PAUSE   = 0x02;
    int RESUME  = 0x03;


    /**
     * 获取布局文件
     */
    int getLayoutResId();


}
