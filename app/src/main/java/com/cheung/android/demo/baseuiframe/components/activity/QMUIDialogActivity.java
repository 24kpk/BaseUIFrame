package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration.VERTICAL_LIST;

public class QMUIDialogActivity extends BaseUIActivity implements BaseQuickAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String title;


    String[] datas = new String[]{
            "消息类型对话框（蓝色按钮）",
            "消息类型对话框（红色按钮）",
            "菜单类型对话框",
            "带 Checkbox 的消息确认框",
            "单选菜单类型对话框",
            "多选菜单类型对话框",
            "多选菜单类型对话框(item 数量很多)",
            "带输入框的对话框",
            "高度适应键盘升降的对话框"
    };
    List<String> listData;
    QMUIDialogListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, VERTICAL_LIST));

        adapter = new QMUIDialogListAdapter(android.R.layout.simple_list_item_1, null);
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
        return R.layout.activity_qmuidialog;
    }

    @Override
    public void onItemClick(View view, int i) {
        switch (i) {
            case 0://消息类型对话框（蓝色按钮）
                showMessagePositiveDialog();
                break;
            case 1://消息类型对话框（红色按钮）
                showMessageNegativeDialog();
                break;
            case 2://菜单类型对话框
                break;
            case 3://带 Checkbox 的消息确认框
                break;
            case 4://单选菜单类型对话框
                break;
            case 5://多选菜单类型对话框
                break;
            case 6://多选菜单类型对话框(item 数量很多)
                break;
            case 7://带输入框的对话框
                break;
            case 8://高度适应键盘升降的对话框
                break;
        }
    }

    /**
     * 消息类型对话框（蓝色按钮）
     */
    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(mActivity)
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        ToastUtil.showToast("点击了确定按钮");
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 消息类型对话框（红色按钮）
     */
    private void showMessageNegativeDialog() {
        new QMUIDialog.MessageDialogBuilder(mActivity)
                .setTitle("标题")
                .setMessage("确定要删除吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).addAction(0,"删除",QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        ToastUtil.showToast("点击了确定按钮");
                        dialog.dismiss();
                    }
                })
                .show();
    }

    class QMUIDialogListAdapter extends BaseQuickAdapter<String> {

        public QMUIDialogListAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(android.R.id.text1, s);
        }
    }
}
