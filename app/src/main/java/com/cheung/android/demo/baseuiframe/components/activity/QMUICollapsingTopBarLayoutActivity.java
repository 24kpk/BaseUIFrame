package com.cheung.android.demo.baseuiframe.components.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheung.android.base.baseuiframe.activity.BaseActivityStack;
import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.log.Print;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUICollapsingTopBarLayoutActivity extends BaseUIActivity {

    String title;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.collapsing_topbar_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    @BindView(R.id.topbar) QMUITopBar mTopBar;



    String[] datas = new String[]{
            "测试数据消息类型对话框（蓝色按钮）",
            "测试数据消息类型对话框（红色按钮）",
            "测试数据菜单类型对话框",
            "测试数据带 Checkbox 的消息确认框",
            "测试数据单选菜单类型对话框",
            "测试数据多选菜单类型对话框",
            "测试数据多选菜单类型对话框(item 数量很多的时候 会看不到确定、取消Action按钮)",
            "测试数据带输入框的对话框",
            "测试数据高度适应键盘升降的对话框(随键盘升降自动调整 Dialog 高度的 Builder)",
            "测试数据消息类型对话框（蓝色按钮）",
            "测试数据消息类型对话框（红色按钮）",
            "测试数据菜单类型对话框",
            "测试数据带 Checkbox 的消息确认框",
            "测试数据单选菜单类型对话框",
            "测试数据多选菜单类型对话框",
            "测试数据多选菜单类型对话框(item 数量很多的时候 会看不到确定、取消Action按钮)",
            "测试数据带输入框的对话框",
            "测试数据消息类型对话框（蓝色按钮）",
            "测试数据消息类型对话框（红色按钮）",
            "测试数据菜单类型对话框",
            "测试数据带 Checkbox 的消息确认框",
            "测试数据单选菜单类型对话框",
            "测试数据多选菜单类型对话框",
            "测试数据多选菜单类型对话框(item 数量很多的时候 会看不到确定、取消Action按钮)",
            "测试数据带输入框的对话框",
            "测试数据消息类型对话框（蓝色按钮）",
            "测试数据消息类型对话框（红色按钮）",
            "测试数据菜单类型对话框",
            "测试数据带 Checkbox 的消息确认框",
            "测试数据单选菜单类型对话框",
            "测试数据多选菜单类型对话框",
            "测试数据多选菜单类型对话框(item 数量很多的时候 会看不到确定、取消Action按钮)",
            "测试数据带输入框的对话框"
    };
    List<String> listData = new ArrayList<>();
    TestDateListAdapter adapter;
    @Override
    protected boolean useTopBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            mTopBar.setTitle(title);
            mCollapsingTopBarLayout.setTitle(title);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));

        for (String data : datas) {
            listData.add(data);
        }
        mCollapsingTopBarLayout.setScrimUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Print.d("scrim: " + animation.getAnimatedValue());
            }
        });


        adapter = new TestDateListAdapter(android.R.layout.simple_list_item_1,listData);
        mRecyclerView.setAdapter(adapter);



        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivityStack.getInstance().finishActivity(QMUICollapsingTopBarLayoutActivity.class);
            }
        });

        ToastUtil.showToast("测试集成 有问题");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuicollapsing_top_bar_layout;
    }

    class TestDateListAdapter extends BaseQuickAdapter<String>{

        public TestDateListAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(android.R.id.text1,s);
        }
    }
}
