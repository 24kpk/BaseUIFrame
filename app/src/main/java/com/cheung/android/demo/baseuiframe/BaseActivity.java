package com.cheung.android.demo.baseuiframe;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;

import butterknife.ButterKnife;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: 自定义二次包装实现基类
 */
public abstract class BaseActivity extends BaseUIActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
    }
}
