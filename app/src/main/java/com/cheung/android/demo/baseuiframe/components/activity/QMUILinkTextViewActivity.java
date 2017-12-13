package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.textview.QMUILinkTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUILinkTextViewActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @BindView(R.id.link_text_view)
    QMUILinkTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }


        textView.setOnLinkClickListener(new QMUILinkTextView.OnLinkClickListener() {
            @Override
            public void onTelLinkClick(String phoneNumber) {
                ToastUtil.showToast("onTelLinkClick-->"+phoneNumber);
            }

            @Override
            public void onMailLinkClick(String mailAddress) {
                ToastUtil.showToast("onMailLinkClick-->"+mailAddress);
            }

            @Override
            public void onWebUrlLinkClick(String url) {
                ToastUtil.showToast("onWebUrlLinkClick-->"+url);
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuilink_text_view;
    }
}
