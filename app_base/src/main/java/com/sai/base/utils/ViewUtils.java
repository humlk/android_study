package com.sai.base.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import java.lang.reflect.Field;

public class ViewUtils {

    public static <V extends View> V findView(View view, int id) {
        return (V) view.findViewById(id);
    }

    public static void visible(View... views) {
        setVisibility(View.VISIBLE, views);
    }

    public static void inVisible(View... views) {
        setVisibility(View.INVISIBLE, views);
    }

    public static void gone(View... views) {
        setVisibility(View.GONE, views);
    }

    public static void setVisibility(int visibility, View... views) {

        for (View view : views) {
            if (view.getVisibility() != visibility) {
                view.setVisibility(visibility);
            }
        }
    }


    public static void loadWebView(WebView webView, String data) {
        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }

    public static void enableView(View view, boolean enable) {
        view.setEnabled(enable);
    }

    public static void openBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;
        float ww = 480f;
        int sampleSize = 1;
        if (w > h && w > ww) {
            sampleSize = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            sampleSize = (int) (newOpts.outHeight / hh);
        }
        if (sampleSize <= 0) {
            sampleSize = 1;
        }
        newOpts.inSampleSize = sampleSize;
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        return bitmap;
    }

    public static void clearTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void translucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public static int getStatusBarHeight(Application context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {

        }
        return 0;
    }

    public static void setBuyLayoutAnimator(final View view, boolean isShowAnimal) {
        if (view == null ) {
            return;
        }
        if (!isShowAnimal) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        final AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotationX", 90, -15, 15, 0), ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1));
        mAnimatorSet.setDuration(800);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                mAnimatorSet.start();
            }
        }, 150);
    }
}
