package com.cheung.android.demo.baseuiframe.components.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIKeyboardHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
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
            "多选菜单类型对话框(item 数量很多的时候 会看不到确定、取消Action按钮)",
            "带输入框的对话框",
            "高度适应键盘升降的对话框(自定义Dialog)"
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
                showMenuDialog();
                break;
            case 3://带 Checkbox 的消息确认框
                showConfirmMessageDialog();
                break;
            case 4://单选菜单类型对话框
                showSingleChoiceDialog();
                break;
            case 5://多选菜单类型对话框
                showMultiChoiceDialog();
                break;
            case 6://多选菜单类型对话框(item 数量很多)
                showNumerousMultiChoiceDialog();
                break;
            case 7://带输入框的对话框
                showEditTextDialog();
                break;
            case 8://高度适应键盘升降的对话框
                showAutoDialog();
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
                }).addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                ToastUtil.showToast("点击了确定按钮");
                dialog.dismiss();
            }
        })
                .show();
    }

    /**
     * 菜单类型对话框 直接选择确认不带Check选项标识
     */
    private void showMenuDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        new QMUIDialog.MenuDialogBuilder(mActivity)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast("你选择了 " + items[which]);
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 带 Checkbox 的消息确认框
     */
    private void showConfirmMessageDialog() {
        new QMUIDialog.CheckBoxMessageDialogBuilder(mActivity)
                .setTitle("退出后是否删除账号信息？")
                .setMessage("删除账号信息")
                .setChecked(true)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).addAction("退出", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                ToastUtil.showToast("点击了退出按钮");
                dialog.dismiss();
            }
        }).show();

    }


    /**
     * 单选菜单类型对话框
     */
    private void showSingleChoiceDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        final int checkedIndex = 0;
        new QMUIDialog.CheckableDialogBuilder(mActivity)
                .setCheckedIndex(checkedIndex)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast("你选择了 " + items[which]);
                        dialog.dismiss();
                    }
                }).show();
    }


    /**
     * 多选菜单类型对话框
     */
    private void showMultiChoiceDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8"};
        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(mActivity)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setCheckedItems(new int[]{1, 3, 5});
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                String info = "您选择了 ";
                for (int i : builder.getCheckedItemIndexes()) {
                    info += i + ";";
                }
                ToastUtil.showToast(info);
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 多选菜单类型对话框(item 数量很多)
     */
    private void showNumerousMultiChoiceDialog() {
        final String[] items = new String[]{
                "选项1", "选项2", "选项3", "选项4", "选项5", "选项6",
                "选项7", "选项8", "选项9", "选项10", "选项11", "选项12",
                "选项13", "选项14", "选项15", "选项16", "选项17", "选项18"
        };
        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(mActivity)
                .setCheckedItems(new int[]{1, 3})
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.setTitle("多选菜单类型对话框(item 数量很多)");
        builder.addAction("提交", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                String result = "你选择了 ";
                for (int i : builder.getCheckedItemIndexes()) {
                    result += i + "; ";
                }
                ToastUtil.showToast(result);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 带输入框的对话框
     */
    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(mActivity);
        builder.setTitle("标题")
                .setPlaceholder("在此输入您的昵称")//HIDEN提示文字
                .setInputType(InputType.TYPE_CLASS_TEXT)//输入框的键盘类型
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            ToastUtil.showToast("您的昵称: " + text);
                            dialog.dismiss();
                        } else {
                            ToastUtil.showToast("请填入昵称");
                        }
                    }
                });
        builder.show();
    }


    /**
     * 高度适应键盘升降的对话框
     */
    private void showAutoDialog() {
        QMAutoTestDialogBuilder autoTestDialogBuilder = (QMAutoTestDialogBuilder) new QMAutoTestDialogBuilder(mActivity)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        ToastUtil.showToast("你点了确定");
                        dialog.dismiss();
                    }
                });
        autoTestDialogBuilder.show();
        QMUIKeyboardHelper.showKeyboard(autoTestDialogBuilder.getEditText(), true);
    }


    class QMAutoTestDialogBuilder extends QMUIDialog.AutoResizeDialogBuilder {
        private Context context;
        private EditText mEditText;

        public QMAutoTestDialogBuilder(Context context) {
            super(context);
            this.context = context;
        }

        public EditText getEditText() {
            return mEditText;
        }


        @Override
        public View onBuildContent(QMUIDialog dialog, ScrollView parent) {
            //初始化布局
            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            //设置布局参数
            layout.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            int padding = QMUIDisplayHelper.dp2px(mContext, 20);//内边距20dp
            layout.setPadding(padding, padding, padding, padding);
            //初始化组件
            mEditText = new EditText(mContext);
            QMUIViewHelper.setBackgroundKeepingPadding(mEditText, QMUIResHelper.getAttrDrawable(mContext, R.attr.qmui_list_item_bg_with_border_bottom));
            mEditText.setHint("输入框");
            LinearLayout.LayoutParams editTextLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, QMUIDisplayHelper.dpToPx(50));
            editTextLP.bottomMargin = QMUIDisplayHelper.dp2px(mActivity, 15);
            mEditText.setLayoutParams(editTextLP);
            layout.addView(mEditText);

            TextView textView = new TextView(mContext);
            textView.setLineSpacing(QMUIDisplayHelper.dp2px(mActivity, 4), 1.0f);
            textView.setText("观察聚焦输入框后，键盘升起降下时 dialog 的高度自适应变化。\n\n" +
                    "QMUI Android 的设计目的是用于辅助快速搭建一个具备基本设计还原效果的 Android 项目，" +
                    "同时利用自身提供的丰富控件及兼容处理，让开发者能专注于业务需求而无需耗费精力在基础代码的设计上。" +
                    "不管是新项目的创建，或是已有项目的维护，均可使开发效率和项目质量得到大幅度提升。");
            textView.setTextColor(ContextCompat.getColor(mActivity, R.color.app_color_description));
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(textView);

            return layout;
        }
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
