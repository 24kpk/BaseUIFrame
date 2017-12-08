package com.cheung.android.base.baseuiframe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheung.android.base.baseuiframe.R;
import com.cheung.android.base.baseuiframe.interfaces.IActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: Activity基类
 */
public abstract class BaseUIActivity extends AppCompatActivity implements IActivity {
    protected Activity mActivity;

    private View mBaseView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        BaseActivityStack.getInstance().addActivity(this);
        setContentView(getLayoutResId());
    }


    /**
     * 默认使用R.layout.container_nor这个头布局
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.container_nor);
        ViewGroup clay = (ViewGroup) findViewById(R.id.parentId);
        QMUITopBar mTopBar = clay.findViewById(R.id.topbar);
        View view = LayoutInflater.from(this).inflate(layoutResID, clay, false);
        clay.addView(view);
        if (useTopBar()) {
            mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
            mTopBar.setTitle(getResources().getString(R.string.app_name));
        }else {
            mTopBar.setVisibility(View.GONE);
        }
        view.setFitsSystemWindows(true);
        QMUIViewHelper.requestApplyInsets(mActivity.getWindow());
        initSystemBarTint();
    }

    /**
     * 默认沉浸式状态栏
     */
    private void initSystemBarTint() {
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }

    /**
     * 是否使用app统一的Title layout，如果需要自定义，复写该函数
     *
     * @return
     */
    protected boolean useTopBar() {
        return true;
    }


    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
}
