package com.x4fhuozhu.app.http.util;


public interface OnSuccessAndFailureListener {
    void onSuccess(String result);

    void onFailure(String errorMsg);
}
