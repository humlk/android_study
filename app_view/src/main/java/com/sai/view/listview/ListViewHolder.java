package com.sai.view.listview;

import android.util.SparseArray;
import android.view.View;


public class ListViewHolder {
    private SparseArray viewMap;
    private View mItemView;

    public ListViewHolder(View view) {
        mItemView = view;
        viewMap = new SparseArray();
    }

    public <T extends View> T getView(int id) {
        if (viewMap.get(id) != null) {
            return (T) viewMap.get(id);
        } else {
            T tv = (T) mItemView.findViewById(id);
            viewMap.put(id, tv);
            return tv;
        }
    }

    public View itemView(){
        return mItemView;
    }
}
