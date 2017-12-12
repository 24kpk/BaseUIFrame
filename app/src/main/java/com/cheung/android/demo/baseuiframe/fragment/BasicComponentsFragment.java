package com.cheung.android.demo.baseuiframe.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheung.android.base.baseuiframe.activity.BaseFragment;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.DividerItemDecoration;
import com.cheung.android.base.baseuiframe.recyclerview.decorator.GridDividerItemDecoration;
import com.cheung.android.demo.baseuiframe.MyApp;
import com.cheung.android.demo.baseuiframe.R;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIDialogActivity;
import com.cheung.android.demo.baseuiframe.components.activity.QMUIFloatLayoutActivity;
import com.cheung.android.demo.baseuiframe.components.activity.RoundButtonActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

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

        //线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL_LIST));

//        //表格布局
//        recyclerView.setLayoutManager(new GridLayoutManager(mActivity,3));
//        //设置分割线
//        recyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), 3));

        adapter = new BasicComponentsListAdapter(android.R.layout.simple_list_item_1, null);
        recyclerView.setAdapter(adapter);

        adapter.setNewData(listDate);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = null;
                switch (i) {
                    case 0://RoundButton
                        intent = new Intent(mActivity, RoundButtonActivity.class);

                        break;
                    case 1://QMUIDialog
                        intent = new Intent(mActivity, QMUIDialogActivity.class);
                        break;
                    case 2://QMUIFloatLayoutActivity
                        intent = new Intent(mActivity,QMUIFloatLayoutActivity.class);
                        break;
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
