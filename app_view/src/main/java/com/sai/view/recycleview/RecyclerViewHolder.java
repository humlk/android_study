package com.sai.view.recycleview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray viewMap;
    private View convertView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.convertView = itemView;
        viewMap = new SparseArray();
    }

    public <T extends View> T getView(int id) {
        if (viewMap.get(id) != null) {
            return (T) viewMap.get(id);
        } else {
            T tv = (T) convertView.findViewById(id);
            viewMap.put(id, tv);
            return tv;
        }
    }
}
