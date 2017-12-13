package com.cheung.android.demo.baseuiframe.components.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.span.QMUIAlignMiddleImageSpan;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIDrawableHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUISpanActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @BindView(R.id.alignMiddle)
    TextView mAlignMiddleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }


        int alignMiddleIconLength = QMUIDisplayHelper.dp2px(mActivity, 20);
        final float spanWidthCharacterCount = 2f;
        SpannableString spannable = new SpannableString("[icon]" + "这是一行示例文字，前面的 Span 设置了和文字垂直居中并占 " + spanWidthCharacterCount + " 个中文字的宽度");
        Drawable iconDrawable = QMUIDrawableHelper.createDrawableWithSize(getResources(), alignMiddleIconLength, alignMiddleIconLength, QMUIDisplayHelper.dp2px(mActivity, 4), ContextCompat.getColor(mActivity, R.color.app_color_theme_3));
        if (iconDrawable != null) {
            iconDrawable.setBounds(0, 0, iconDrawable.getIntrinsicWidth(), iconDrawable.getIntrinsicHeight());
        }
        ImageSpan alignMiddleImageSpan = new QMUIAlignMiddleImageSpan(iconDrawable, QMUIAlignMiddleImageSpan.ALIGN_MIDDLE, spanWidthCharacterCount);
        spannable.setSpan(alignMiddleImageSpan, 0, "[icon]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mAlignMiddleTextView.setText(spannable);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuispan;
    }
}
