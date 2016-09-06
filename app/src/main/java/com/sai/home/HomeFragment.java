package com.sai.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sai.R;
import com.sai.base.BaseFragment;
import com.sai.utils.ToastUtil;
import com.sai.view.recycleview.RecyclerListView;
import com.sai.view.recycleview.RecyclerListViewAdapter;
import com.sai.view.recycleview.RecyclerViewHolder;
import com.sai.view.refreshLayout.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements RecyclerListViewAdapter.OnRecyclerItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerListView)
    RecyclerListView mRecyclerListView;
    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;


    List<DoorInfo> mDoorInfoList = new ArrayList<>();
    RecyclerListViewAdapter mViewAdapter;

    private final String buss_flag = "android.intent.action.mybuss";

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int inflateLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDoorInfoList();
        mViewAdapter = new RecyclerListViewAdapter<DoorInfo>(getActivity(),R.layout.item_home_list,mDoorInfoList) {
            @Override
            public void convert(RecyclerViewHolder holder, DoorInfo item, int position) {
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.label);
            }
        };
        mRecyclerListView.setAdapter(mViewAdapter);
        mViewAdapter.setOnItemClickListener(this);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ToastUtil.show("refresh data");
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getDoorInfoList() {
        Intent myIntent = new Intent();
        myIntent.setAction(buss_flag);
        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> infoList = pm.queryIntentActivities(myIntent, 0);

        for (ResolveInfo info : infoList) {
            addItem(info.activityInfo.loadLabel(pm) + "", activityIntent(info.activityInfo.packageName, info.activityInfo.name));
        }
    }

    private void addItem(String label, Intent intent) {
        DoorInfo doorInfo = new DoorInfo();
        doorInfo.label = label;
        doorInfo.intent = intent;
        mDoorInfoList.add(doorInfo);
    }

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    @Override
    public void onItemClick(RecyclerListViewAdapter adapter, View view, int position) {
        DoorInfo doorInfo = (DoorInfo)adapter.getObject(position);
        startActivity(doorInfo.intent);
    }

    class DoorInfo {
        public String label;
        public Intent intent;
    }
}
