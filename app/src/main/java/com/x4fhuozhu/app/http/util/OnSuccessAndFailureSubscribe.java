package com.x4fhuozhu.app.http.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


import io.reactivex.observers.DisposableObserver;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;


public class OnSuccessAndFailureSubscribe extends DisposableObserver<ResponseBody>
        implements ProgressCancelListener {
    /**
     * 是否需要显示默认Loading
     */
    private boolean showProgress = true;
    private OnSuccessAndFailureListener mOnSuccessAndFailureListener;

    private Context context;
    private ProgressDialog progressDialog;


    /**
     * @param mOnSuccessAndFailureListener 成功回调监听
     */
    public OnSuccessAndFailureSubscribe(OnSuccessAndFailureListener mOnSuccessAndFailureListener) {
        this.mOnSuccessAndFailureListener = mOnSuccessAndFailureListener;
    }


    /**
     * @param mOnSuccessAndFailureListener 成功回调监听
     * @param context                    上下文
     */
    public OnSuccessAndFailureSubscribe(OnSuccessAndFailureListener mOnSuccessAndFailureListener, Context context) {
        this.mOnSuccessAndFailureListener = mOnSuccessAndFailureListener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }


    /**
     * @param mOnSuccessAndFailureListener 成功回调监听
     * @param context                    上下文
     * @param showProgress               是否需要显示默认Loading
     */
    public OnSuccessAndFailureSubscribe(OnSuccessAndFailureListener mOnSuccessAndFailureListener, Context context, boolean showProgress) {
        this.mOnSuccessAndFailureListener = mOnSuccessAndFailureListener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        this.showProgress = showProgress;
    }


    private void showProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.show();
        }
    }


    private void dismissProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.dismiss();
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        LogUtil.e("开始订阅");
        showProgressDialog();
    }


    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        LogUtil.e("完成订阅");
        dismissProgressDialog();
        progressDialog = null;
    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        try {
            LogUtil.e("调用出错");
            if (e instanceof SocketTimeoutException) {
                mOnSuccessAndFailureListener.onFailure("网络连接超时");
            } else if (e instanceof ConnectException) {
                mOnSuccessAndFailureListener.onFailure("网络连接超时");
            } else if (e instanceof SSLHandshakeException) {
                mOnSuccessAndFailureListener.onFailure("安全证书异常");
            } else if (e instanceof HttpException) {
                //请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    mOnSuccessAndFailureListener.onFailure("网络异常，请检查您的网络状态");
                } else if (code == 404) {
                    mOnSuccessAndFailureListener.onFailure("请求的地址不存在");
                } else {
                    mOnSuccessAndFailureListener.onFailure("请求失败");
                }
            } else if (e instanceof UnknownHostException) {
                mOnSuccessAndFailureListener.onFailure("域名解析失败");
            } else {
                mOnSuccessAndFailureListener.onFailure("error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFailureSubscribe", "error:" + e.getMessage());
            dismissProgressDialog();
            progressDialog = null;

        }

    }


    /**
     * 当result等于1回调给调用者，否则自动显示错误信息，若错误信息为401跳转登录页面。
     * ResponseBody  body = response.body();//获取响应体
     * InputStream inputStream = body.byteStream();//获取输入流
     * byte[] bytes = body.bytes();//获取字节数组
     * String str = body.string();//获取字符串数据
     */
    @Override
    public void onNext(ResponseBody body) {
        try {
            LogUtil.e("调用onNext");
            mOnSuccessAndFailureListener.onSuccess(body.string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }
}
