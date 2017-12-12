package com.cheung.android.demo.baseuiframe.components.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.log.Print;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUIEmptyViewActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;


    @BindView(R.id.emptyView)
    QMUIEmptyView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }
        emptyView.show(true, "titleText", "detailText", "buttonText", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("buttonText Button is clicked");
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuiempty_view;
    }
}
