package com.sai.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class BaseFragment extends Fragment{

    protected Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int infId = inflateLayout();
        if(infId == 0){
            return null;
        }
        View view = inflater.inflate(infId, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    protected int inflateLayout(){
        return 0;
    }

    @Override
    public void onDestroyView() {
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroyView();
    }
}
