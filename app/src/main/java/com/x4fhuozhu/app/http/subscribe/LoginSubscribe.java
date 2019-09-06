package com.x4fhuozhu.app.http.subscribe;


import com.x4fhuozhu.app.http.util.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

import okhttp3.ResponseBody;

public class LoginSubscribe {
    /**
     * 获取短信
     */
    public static void sendSms(String phone, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().sendSms(phone);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
    /**
     * 登录提交
     */
    public static void doLogin(String phone,String code, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().doLogin(phone,code);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
