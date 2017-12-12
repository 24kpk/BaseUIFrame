package com.cheung.android.demo.baseuiframe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.cheung.android.base.baseuiframe.activity.BaseActivityStack;
import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.fragment.FourFragment;
import com.cheung.android.demo.baseuiframe.fragment.BasicComponentsFragment;
import com.cheung.android.demo.baseuiframe.fragment.ThrFragment;
import com.cheung.android.demo.baseuiframe.fragment.TwoFragment;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseUIActivity {
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;


    private MainFPagerAdaper mainFPagerAdaper;
    private long mLastClickReturnTime ;

    //不使用通用TITLE
    @Override
    protected boolean useTopBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);

        int normalColor = QMUIResHelper.getAttrColor(mActivity, R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(mActivity, R.attr.qmui_config_color_blue);

        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);

        //平分屏幕宽度
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        //设置显示底标 默认不显示
        mTabSegment.setHasIndicator(true);
        //设置底标位置 默认在下方
        mTabSegment.setIndicatorPosition(true);

        //必须调用mTabSegment.setupWithViewPager(mContentViewPager,false);和ViewPager关联起来才回展示TAB
        mTabSegment.addTab(new QMUITabSegment.Tab(
                "基础组件"
        )).addTab(new QMUITabSegment.Tab(
                "TWO"
        )).addTab(new QMUITabSegment.Tab(
                "THR"
        )).addTab(new QMUITabSegment.Tab(
                "FOUR"
        ));

        mainFPagerAdaper = new MainFPagerAdaper(getSupportFragmentManager());
        mContentViewPager.setOffscreenPageLimit(3);
        mContentViewPager.setAdapter(mainFPagerAdaper);


        mTabSegment.setupWithViewPager(mContentViewPager,false);

    }


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }






    ////////////////////////////////////////页面适配器start//////////////////////////////////////////////
    public class MainFPagerAdaper extends FragmentPagerAdapter {

        public MainFPagerAdaper(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment retFragment = null;
            switch (position) {
                case 0:
                    retFragment = new BasicComponentsFragment();
                    break;
                case 1:
                    retFragment = new TwoFragment();
                    break;
                case 2:
                    retFragment = new ThrFragment();
                    break;
                case 3:
                    retFragment = new FourFragment();
                    break;
            }
            return retFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
    ////////////////////////////////////////页面适配器end//////////////////////////////////////////////


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                //moveTaskToBack(true);
                if(System.currentTimeMillis() - mLastClickReturnTime > 1000L) {
                    mLastClickReturnTime = System.currentTimeMillis();
                    ToastUtil.showToast("再按一次退出程序");
                    return true;
                }else {
                    BaseActivityStack.getInstance().appExit(mActivity);
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
