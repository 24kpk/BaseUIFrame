package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.util.QMUILangHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUIVerticalTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUIVerticalTextViewActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @BindView(R.id.verticalTextView)
    QMUIVerticalTextView mVerticalTextView;
    @BindView(R.id.verticalTextView_editText)
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }

        final String defaultText = String.format("%s 实现对文字的垂直排版。并且对非 CJK (中文、日文、韩文)字符做90度旋转排版。可以在下方的输入框中输入文字，体验不同文字垂直排版的效果。",
                QMUIVerticalTextView.class.getSimpleName());
        mVerticalTextView.setText(defaultText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(QMUILangHelper.isNullOrEmpty(s)) {
                    mVerticalTextView.setText(defaultText);
                }else {
                    mVerticalTextView.setText(s);
                }
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuivertical_text_view;
    }
}
