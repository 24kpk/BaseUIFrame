package com.cheung.android.demo.baseuiframe.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheung.android.base.baseuiframe.activity.BaseFragment;
import com.cheung.android.base.baseuiframe.log.Print;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.GridDividerItemDecoration;
import com.cheung.android.base.baseuiframe.utils.ToastUtil;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIBottomSheetActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUICollapsingTopBarLayoutActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIDialogActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIEmptyViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIFloatLayoutActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIGroupListViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUILinkTextViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIPopupActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIProgressBarActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIPullRefreshLayoutActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIQQFaceViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIRadiusImageViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUISpanActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUISpanTouchFixTextViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUITipDialogActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIVerticalTextViewActivity;
import com.cheung.android.demo.baseuiframe.components.activity.RoundButtonActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration.VERTICAL_LIST;

/**
 * author: C_CHEUNG
 * created on: 2017/12/11
 * description: 基础组件练习
 */
public class BasicComponentsFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<String> listDate;
    private BasicComponentsListAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initUI(View parentView) {
        ButterKnife.bind(this, parentView);
        topBar.setTitle("基础组件");
        topBar.removeAllLeftViews();

        listDate = new ArrayList<>();
        String[] date = getActivity().getResources().getStringArray(R.array.ary_basic_components);
        for (String s : date) {
            listDate.add(s);
        }

//        //线性布局
//        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        //设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL_LIST));

        //表格布局
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity,3));
        //设置分割线
        recyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), 3));

        adapter = new BasicComponentsListAdapter(android.R.layout.simple_list_item_1, null);
        recyclerView.setAdapter(adapter);

        adapter.setNewData(listDate);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = null;
                final QMUITipDialog dialog =  new QMUITipDialog.Builder(mActivity).setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL).setTipWord("稍后测试该控件").create();
                switch (i) {
                    case 0://RoundButton
                        intent = new Intent(mActivity, RoundButtonActivity.class);

                        break;
                    case 1://QMUIDialog
                        intent = new Intent(mActivity, QMUIDialogActivity.class);
                        break;
                    case 2://QMUIFloatLayout
                        intent = new Intent(mActivity,QMUIFloatLayoutActivity.class);
                        break;
                    case 3://QMUIEmptyView
                        intent = new Intent(mActivity,QMUIEmptyViewActivity.class);
                        break;
                    case 4://QMUITabSegment
                        ToastUtil.showToast("QMUITabSegment 当前首页即为QMUITabSegment");
                        return;
                    case 5://QMUIProgress
                        intent = new Intent(mActivity,QMUIProgressBarActivity.class);
                        break;
                    case 6://QMUIBottomSheet
                        intent = new Intent(mActivity,QMUIBottomSheetActivity.class);
                        break;
                    case 7://QMUIGroupListView
                        intent = new Intent(mActivity, QMUIGroupListViewActivity.class);
                        break;
                    case 8://QMUITipDialog
                        intent = new Intent(mActivity,QMUITipDialogActivity.class);
                        break;
                    case 9://QMUIRadiusImageView
                        intent = new Intent(mActivity,QMUIRadiusImageViewActivity.class);
                        break;
                    case 10://QMUIVerticalTextView
                        intent = new Intent(mActivity,QMUIVerticalTextViewActivity.class);
                        break;
                    case 11://QMUIPullRefreshLayout
                        intent = new Intent(mActivity,QMUIPullRefreshLayoutActivity.class);
                        break;
                    case 12://QMUIPopup
                        intent = new Intent(mActivity,QMUIPopupActivity.class);
                        break;
                    case 13://QMUISpanTouchFixTextView
                        dialog.setCancelable(true);
                        dialog.show();
//                        intent = new Intent(mActivity,QMUISpanTouchFixTextViewActivity.class);
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },1500);
                        break;
                    case 14://QMUILinkTextView
                        intent = new Intent(mActivity,QMUILinkTextViewActivity.class);
                        break;
                    case 15://QMUIQQFaceView
                        dialog.setCancelable(true);
                        dialog.show();
//                        intent = new Intent(mActivity,QMUIQQFaceViewActivity.class);
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },1500);
                        break;
                    case 16://Span
                        dialog.setCancelable(true);
                        dialog.show();
//                        intent = new Intent(mActivity,QMUISpanTouchFixTextViewActivity.class);
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },1500);
//                        intent = new Intent(mActivity,QMUISpanActivity.class);
                        break;
                    case 17:
                        Print.d("22222222222222");
                        intent = new Intent(mActivity,QMUICollapsingTopBarLayoutActivity.class);
                        break;


                }
                if(intent == null) {
                    return;
                }
                intent.putExtra(MyApp.INTENT_VALUE_TITLE_STR, listDate.get(i));
                startActivity(intent);

            }
        });
    }

    class BasicComponentsListAdapter extends BaseQuickAdapter<String> {

        public BasicComponentsListAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(android.R.id.text1, s);
        }
    }

}
