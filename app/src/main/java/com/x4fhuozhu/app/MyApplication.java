package com.x4fhuozhu.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private List<AppCompatActivity> list = new ArrayList<>();
    private static MyApplication ea;
    public static MyApplication context;
//    private static DaoSession daoSession;
    public MyApplication() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ShareSDK
        context = this;
        //配置数据库greenDao
        setupDatabase();
    }


    private void setupDatabase() {
        //创建数据库shop.db"
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "x4fapp.db", null);
        //获取可写数据库
//        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
//        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
//        daoSession = daoMaster.newSession();
    }

    public static MyApplication getInstance() {
        if (null == ea) {
            ea = new MyApplication();
        }
        return ea;
    }

    public void addActivity(AppCompatActivity activity) {
        list.add(activity);
    }

    /**
     * 退出程序
     *
     * @param context
     */
    public void exit(Context context) {
        for (AppCompatActivity activity : list) {
            activity.finish();
        }
        System.exit(0);
    }

    public static Context c() {
        return context;
    }

//    public static DaoSession getDaoInstant() {
//        return daoSession;
//    }

}
