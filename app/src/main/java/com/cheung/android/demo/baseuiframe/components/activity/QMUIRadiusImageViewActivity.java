package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUIRadiusImageViewActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuiradius_image_view;

    }
}
