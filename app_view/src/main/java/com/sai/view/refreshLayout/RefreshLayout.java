package com.sai.view.refreshLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v4.widget.SwipeRefreshLayout;

public class RefreshLayout extends SwipeRefreshLayout{

    public RefreshLayout(Context context){
        super(context);
        init();
    }
    public RefreshLayout(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }

    private void init(){
//        setColorSchemeResources(R.color.refresh_bar_from, R.color.refresh_bar_to);
    }
}
