package com.sai.base.utils;

import android.util.Log;

public class LogUtils {
    private static String TAG = "sai";

    private static boolean isDebug = true;

    private static int level = Log.DEBUG;

    public static void show(int level, String tag, String msg) {
        if (isDebug && !StringUtils.isEmpty(msg)) {
            Log.println(level, tag, msg);
        }
    }

    public static void show(String tag, String msg) {
        show(level, tag, msg);
    }

    public static void show(String msg) {
        show(level, TAG, msg);
    }

    /**
     * 记录异常日志
     *
     * @param msg
     */
    public static void e(String msg) {
        show(Log.ERROR, TAG, msg);
    }

    /**
     * 记录业务日志
     *
     * @param msg
     */
    public static void i(String msg) {
        show(Log.INFO, TAG, msg);
    }

    /**
     * 记录详细的日志
     *
     * @param msg
     */
    public static void d(String msg) {
        show(Log.DEBUG, TAG, msg);
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }
}
