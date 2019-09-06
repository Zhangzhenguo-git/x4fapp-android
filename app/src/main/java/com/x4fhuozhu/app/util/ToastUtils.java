package com.x4fhuozhu.app.util;

import android.content.Context;
import android.widget.Toast;

import com.x4fhuozhu.app.common.MyApplication;


public final class ToastUtils {
    private static boolean isToastShow = true;
    private ToastUtils() {

    }

    public static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void show(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static void showShort(int resId) {
        showShort(MyApplication.getApp().getString(resId));
    }

    public static void showShort(String msg) {
        Toast.makeText(MyApplication.getApp(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        showLong(MyApplication.getApp().getString(resId));
    }

    public static void showLong(String msg) {
        Toast.makeText(MyApplication.getApp(), msg, Toast.LENGTH_LONG).show();
    }



    public static void makeToast(String text) {
        if (isToastShow) {
            Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeToast(String text, boolean isShort) {
        if (isToastShow) {
            if (isShort) {
                Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_LONG).show();
            }
        }
    }
}