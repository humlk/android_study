package com.sai.transition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sai.R;
import com.sai.base.BaseActivity;
import com.sai.base.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: huajie
 * @version: 1.0
 * @date: 2016/9/5
 * @email: huajie@le.com
 * @Copyright (c) 2016. le.com Inc. All rights reserved.
 */
public class TransitionActivity extends BaseActivity {

    @BindView(R.id.btn_action)
    Button mBtnAction;
    @BindView(R.id.txt_msg)
    TextView mTxtMsg;
    @BindView(R.id.content)
    LinearLayout mContent;

    boolean txtVisibility = false;

    private final String TAG = "TransitionTest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        addWatch("content", mContent);
        addWatch("textview", mTxtMsg);
    }

    private void addWatch(final String tag, final View view) {
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                LogUtils.show(TAG, tag + " ----> onViewAttachedToWindow");
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                view.removeOnAttachStateChangeListener(this);
                LogUtils.show(TAG, tag + " ----> onViewDetachedFromWindow");
            }
        });

        view.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                view.getViewTreeObserver().removeOnDrawListener(this);
                LogUtils.show(TAG,tag + " ----> onDraw");
            }
        });
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                LogUtils.show(TAG,tag + " ----> onPreDraw");
                return false;
            }
        });
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LogUtils.show(TAG,tag + " ----> onGlobalLayout");
            }
        });
    }

    @OnClick(R.id.btn_action)
    public void actionClick() {
        //api19
        TransitionManager.beginDelayedTransition(mContent);
        txtVisibility = !txtVisibility;
        mTxtMsg.setVisibility(txtVisibility ? View.VISIBLE : View.GONE);
    }
}
