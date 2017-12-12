package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.view.View;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QMUIBottomSheetActivity extends BaseUIActivity {
    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
        title = getIntent().getStringExtra(MyApp.INTENT_VALUE_TITLE_STR);
        if (title != null) {
            topBar.setTitle(title);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_qmuibottom_sheet;
    }

    @OnClick({R.id.tv_list, R.id.tv_grid})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_list:
                QMUIBottomSheet sheet = new QMUIBottomSheet.BottomListSheetBuilder(mActivity)
                        .addItem("Item 1")
                        .addItem("Item 2")
                        .addItem("Item 3")
                        .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                            @Override
                            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                                dialog.dismiss();
                                ToastUtil.showToast("Item " + position);
                            }
                        })
                        .build();
                sheet.show();
                break;
            case R.id.tv_grid:
                showSimpleBottomSheetGrid();
                break;
        }
    }


    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(mActivity);
        builder.addItem(R.mipmap.ic_launcher, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "分享到私信", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.ic_launcher, "保存到本地", TAG_SHARE_LOCAL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        switch (tag) {
                            case TAG_SHARE_WECHAT_FRIEND:
                                ToastUtil.showToast("分享到微信");
                                break;
                            case TAG_SHARE_WECHAT_MOMENT:
                                ToastUtil.showToast("分享到朋友圈");
                                break;
                            case TAG_SHARE_WEIBO:
                                ToastUtil.showToast("分享到微博");
                                break;
                            case TAG_SHARE_CHAT:
                                ToastUtil.showToast("分享到私信");
                                break;
                            case TAG_SHARE_LOCAL:
                                ToastUtil.showToast("保存到本地");
                                break;
                        }
                    }
                });
        builder.build().show();
    }
}

