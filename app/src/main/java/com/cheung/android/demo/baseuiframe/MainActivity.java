package com.cheung.android.demo.baseuiframe;

import android.os.Bundle;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.log.Print;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseUIActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        topBar.removeAllLeftViews();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn)
    public void click(){
        Print.d("SSSSSSSSSSSSSSS");
        ToastUtil.showToast("!!!!!!!!!!");
    }

}
