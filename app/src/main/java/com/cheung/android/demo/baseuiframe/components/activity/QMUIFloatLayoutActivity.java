package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUIFloatLayoutActivity extends BaseUIActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;

    @BindView(R.id.qmui_floatlayout)
    QMUIFloatLayout mFloatLayout;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }

        //设置对齐方式 默认左看齐
        mFloatLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        for (int i = 0; i < 8; i++) {
            addItemToFloatLayout(mFloatLayout);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuifloat_layout;
    }


    private void addItemToFloatLayout(QMUIFloatLayout floatLayout){
        int currentChildCount = floatLayout.getChildCount();

        TextView textView = new TextView(mActivity);
        int textViewPadding = QMUIDisplayHelper.dp2px(mActivity, 4);
        textView.setPadding(textViewPadding, textViewPadding, textViewPadding, textViewPadding);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(ContextCompat.getColor(mActivity, R.color.qmui_config_color_white));
        textView.setText(String.valueOf(currentChildCount));
        textView.setBackgroundResource(currentChildCount % 2 == 0 ? R.color.app_color_theme_3 : R.color.app_color_theme_6);

        int textViewSize = QMUIDisplayHelper.dpToPx(60);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(textViewSize, textViewSize);
        floatLayout.addView(textView, lp);
    }
}
