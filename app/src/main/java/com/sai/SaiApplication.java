package com.sai;

import android.app.Application;

/**
 * @author: huajie
 * @version: 1.0
 * @date: 2016/9/5
 * @email: huajie@le.com
 * @Copyright (c) 2016. le.com Inc. All rights reserved.
 */
public class SaiApplication extends Application{

    private static SaiApplication mSaiApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        mSaiApplication = this;
    }

    public static SaiApplication get(){
        return mSaiApplication;
    }
}
