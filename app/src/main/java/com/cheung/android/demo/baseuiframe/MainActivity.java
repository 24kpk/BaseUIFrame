package com.cheung.android.demo.baseuiframe;

import android.os.Bundle;
import android.view.View;

import com.cheung.android.base.baseuiframe.log.Print;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar mQMUITopBar;

    @BindView(R.id.btn)
    QMUIRoundButton mQMUIRoundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mQMUITopBar!=null) {
            mQMUITopBar.removeAllLeftViews();
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn)
    public void click(View view) {
        switch (view.getId()){
            case R.id.btn:
                Print.d("!!!!!!!!!!!!!!!!!!!!!!!");
                ToastUtil.showLongToast("!!!!!!!!!!!!!!!!!!!!!!!");
                break;
        }
    }

}
