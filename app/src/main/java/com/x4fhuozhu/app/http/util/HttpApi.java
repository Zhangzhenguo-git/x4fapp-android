package com.x4fhuozhu.app.http.util;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface HttpApi {
    @POST("user/sendSms")
    @FormUrlEncoded
    Observable<ResponseBody> sendSms(@Field("phone") String phone);

    @POST("user/login")
    @FormUrlEncoded
    Observable<ResponseBody> doLogin(@Field("phone") String phone, @Field("code") String code);

    @POST("{path}")
    @FormUrlEncoded
    Observable<ResponseBody> post(@Path("path") String path, @FieldMap Map<String, String> map);


}
