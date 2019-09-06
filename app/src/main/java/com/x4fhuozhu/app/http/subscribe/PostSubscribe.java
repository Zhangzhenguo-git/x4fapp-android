package com.x4fhuozhu.app.http.subscribe;


import com.x4fhuozhu.app.http.util.RetrofitFactory;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;


public class PostSubscribe {
    /**
     * 获取短信
     */
    public static void post(String path, Map<String, String> map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().post(path, map);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
