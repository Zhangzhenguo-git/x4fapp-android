package com.x4fhuozhu.app.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.common.BaseConstant;

import java.lang.ref.WeakReference;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static String TAG = "WXEntryActivity";

    private IWXAPI api;
    private MyHandler handler;

    private static class MyHandler extends Handler {
        private final WeakReference<WXEntryActivity> wxEntryActivityWeakReference;

        public MyHandler(WXEntryActivity wxEntryActivity) {
            wxEntryActivityWeakReference = new WeakReference<WXEntryActivity>(wxEntryActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            int tag = msg.what;
            switch (tag) {
                case NetworkUtil.GET_TOKEN: {
                    Bundle data = msg.getData();
                    JSONObject json = null;
                    try {
                        json = JSON.parseObject(data.getString("result"));
                        String openId,unionId, accessToken, refreshToken, scope;
                        openId = json.getString("openid");
                        unionId = json.getString("unionid");
                        accessToken = json.getString("access_token");
                        refreshToken = json.getString("refresh_token");
                        scope = json.getString("scope");
                        Log.e("unionId",unionId);
                        Log.e("openId",openId);
                        Log.e("accessToken",accessToken);
                        Log.e("refreshToken",refreshToken);
                        Log.e("scope",scope);
                        Intent intent = new Intent(wxEntryActivityWeakReference.get(), UserInfoActivity.class);
                        intent.putExtra("openId", openId);
                        intent.putExtra("accessToken", accessToken);
                        intent.putExtra("refreshToken", refreshToken);
                        intent.putExtra("scope", scope);
                        wxEntryActivityWeakReference.get().startActivity(intent);
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
                default:;
                break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, BaseConstant.WXAPP_ID, false);
        handler = new MyHandler(this);
        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        Toast.makeText(this, " type=" + req.getType(), Toast.LENGTH_SHORT).show();
    }
    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.errcode_unsupported;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            SendAuth.Resp authResp = (SendAuth.Resp) resp;
            final String code = authResp.code;
            NetworkUtil.sendWxAPI(handler, String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                            "appid=%s&secret=%s&code=%s&grant_type=authorization_code", BaseConstant.WXAPP_ID,
                    BaseConstant.WXAPP_SECRET, code), NetworkUtil.GET_TOKEN);
        }
        Toast.makeText(this, getString(result) + ", type=" + resp.getType(), Toast.LENGTH_SHORT).show();
        finish();
    }
}