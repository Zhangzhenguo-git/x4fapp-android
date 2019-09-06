package com.x4fhuozhu.app.common;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.x4fhuozhu.app.bmap.service.LocationService;
import com.x4fhuozhu.app.http.util.RetrofitFactory;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by azhao
 */

public class MyApplication extends Application {

    public static Context appContext;
    public static ArrayList<Activity> allActivities = new ArrayList<Activity>();
    public static MyApplication app;
    public LocationService locationService;
    public Vibrator mVibrator;
    /**
     * 这个对象是专门用来向微信发送数据的一个重要接口,使用强引用持有,所有的信息发送都是基于这个对象的
     */
    public IWXAPI api;

    /**
     * 注册微信app
     * @param context 上下文对象
     */
    public void registerWeChat(Context context) {
        api = WXAPIFactory.createWXAPI(context, BaseConstant.WXAPP_ID, true);
        api.registerApp(BaseConstant.WXAPP_ID);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        //初始化极光推送
        // 置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        // 初始化 JPush
        JPushInterface.init(this);
        //注册微信
        registerWeChat(appContext);
        //初始化QMUI.ARC
        QMUISwipeBackActivityManager.init(this);
        //初始化网络token
        initToken();
        app = this;
    }

    /**
     * 初始化网络请求token
     */
    private void initToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(BaseConstant.LOGIN, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        RetrofitFactory.getInstance().setAccessToken(token);
    }

    public static Context getContext() {
        return appContext;
    }

    public static MyApplication getApp() {
        return app;
    }

    public static void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public static void delActivity(Activity activity) {
        allActivities.remove(activity);
    }

}
