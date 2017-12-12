package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QMUIProgressBarActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.backBtn)
    Button backBtn;

    @BindView(R.id.rectProgressBar)
    QMUIProgressBar mRectProgressBar;
    @BindView(R.id.circleProgressBar)
    QMUIProgressBar mCircleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }
        mRectProgressBar.setMaxValue(85);
        mCircleProgressBar.setMaxValue(100);
        mRectProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return value + "/" + maxValue;
            }
        });

        mCircleProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });

    }

    @OnClick({R.id.startBtn,R.id.backBtn})
    public void click(View v){
        switch (v.getId()){
            case R.id.startBtn:
                mRectProgressBar.setProgress(85);
                mCircleProgressBar.setProgress(85);
                break;
            case R.id.backBtn:
                mRectProgressBar.setProgress(0);
                mCircleProgressBar.setProgress(0);
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuiprogress_bar;
    }
}
