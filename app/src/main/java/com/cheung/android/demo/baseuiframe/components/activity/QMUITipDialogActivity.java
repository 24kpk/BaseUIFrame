package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUITipDialogActivity extends BaseUIActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    String[] datas = new String[]{
            "Loading 类型提示框",
            "成功提示类型提示框",
            "失败提示类型提示框",
            "信息提示类型提示框",
            "单独图片类型提示框",
            "单独文字类型提示框",
            "自定义内容提示框"
    };
    List<String> listData;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    QMUITipDialogListAdapter adapter;


    QMUITipDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        adapter = new QMUITipDialogListAdapter(android.R.layout.simple_list_item_1, null);
        recyclerView.setAdapter(adapter);

        listData = new ArrayList<>();
        for (String data : datas) {
            listData.add(data);
        }
        adapter.setNewData(listData);

        adapter.setOnRecyclerViewItemClickListener(this);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuitip_dialog;
    }

    @Override
    public void onItemClick(View view, int i) {
        QMUITipDialog.Builder builder = new QMUITipDialog.Builder(mActivity);
        switch (i) {
            case 0://Loading 类型提示框
                dialog = builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("Loading 类型提示框")
                        .create();
                break;
            case 1://成功提示类型提示框
                dialog = builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord("成功提示类型提示框")
                        .create();
                break;
            case 2://失败提示类型提示框
                dialog = builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                        .setTipWord("失败提示类型提示框")
                        .create();
                break;
            case 3://信息提示类型提示框
                dialog = builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                        .setTipWord("信息提示类型提示框")
                        .create();
                break;
            case 4://单独图片类型提示框
                dialog = builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .create();
                break;
            case 5://单独文字类型提示框
                dialog = builder
                        .setTipWord("单独文字类型提示框")
                        .create();
                break;
            case 6://自定义内容提示框
                dialog = new QMUITipDialog.CustomBuilder(mActivity)
                        .setContent(R.layout.tipdialog_custom)
                        .create();
                break;
        }
        if(dialog==null) {
            return;
        }
        dialog.show();

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }


    class QMUITipDialogListAdapter extends BaseQuickAdapter<String> {

        public QMUITipDialogListAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(android.R.id.text1, s);
        }
    }
}
