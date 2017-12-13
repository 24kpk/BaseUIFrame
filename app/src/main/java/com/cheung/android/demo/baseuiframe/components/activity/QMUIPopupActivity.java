package com.cheung.android.demo.baseuiframe.components.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cheung.android.base.baseuiframe.activity.BaseUIActivity;
import com.cheung.android.base.baseuiframe.log.Print;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QMUIPopupActivity extends BaseUIActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;

    String title;

    @BindView(R.id.actiontBtn1)
    Button mActionButton1;
    @BindView(R.id.actiontBtn2)
    Button mActionButton2;

    private QMUIPopup mNormalPopup;
    private QMUIListPopup mListPopup;

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
        return R.layout.activity_qmuipopup;
    }

    @OnClick({R.id.actiontBtn1,R.id.actiontBtn2})
    public void click(View v){
        switch (v.getId()){
            case R.id.actiontBtn1:
                initNormalPopupIfNeed();
                mNormalPopup.setAnimStyle(QMUIPopup.ANIM_AUTO);
                mNormalPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
                mNormalPopup.show(v);
                mActionButton1.setText("NormalPopup正在展示");
                break;
            case R.id.actiontBtn2:
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
                mListPopup.show(v);
                mActionButton2.setText("ListPopup正在展示");
                break;
        }
    }

    private void initNormalPopupIfNeed() {
        if(mNormalPopup==null) {
            mNormalPopup = new QMUIPopup(mActivity, QMUIPopup.DIRECTION_NONE);
            TextView textView = new TextView(mActivity);
            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
                    QMUIDisplayHelper.dp2px(mActivity, 250),
                    WRAP_CONTENT
            ));
            textView.setLineSpacing(QMUIDisplayHelper.dp2px(mActivity, 4), 1.0f);
            int padding = QMUIDisplayHelper.dp2px(mActivity, 20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText("Popup 可以设置其位置以及显示和隐藏的动画");
            textView.setTextColor(ContextCompat.getColor(mActivity, R.color.app_color_description));
            mNormalPopup.setContentView(textView);
            mNormalPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    mActionButton1.setText("显示普通浮层");
                }
            });
        }
    }


    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            String[] listItems = new String[]{
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4",
                    "Item 5",
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            ArrayAdapter adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, data);

            mListPopup = new QMUIListPopup(mActivity, QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(mActivity, 250), QMUIDisplayHelper.dp2px(mActivity, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ToastUtil.showToast("Item " + (i + 1));
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    mActionButton2.setText("显示列表浮层");
                }
            });
        }
    }
}
