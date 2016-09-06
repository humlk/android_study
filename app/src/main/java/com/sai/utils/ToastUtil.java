package com.sai.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.sai.SaiApplication;


public class ToastUtil {

    public static void show(String msg){
        Toast.makeText(SaiApplication.get(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void show(View view, String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_INDEFINITE).setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("action click");
            }
        }).show();
    }
}
