package com.x4fhuozhu.app.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.x4fhuozhu.app.common.BaseConstant;

import org.json.JSONObject;

public class PayActivity extends Activity {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, BaseConstant.WXAPP_ID);
    }

    public void doPay() {
        String url = "https://wxpay.wxutil.com/pub_v2/app/app_pay.php";
        Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
        try {
            byte[] buf = WxUtil.getHtmlByteArray(url);
            if (buf != null && buf.length > 0) {
                String content = new String(buf);
                Log.e("get server pay params:", content);
                JSONObject json = new JSONObject(content);
                if (null != json && !json.has("retcode")) {
                    PayReq req = new PayReq();
                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                    req.appId = json.getString("appid");
                    req.partnerId = json.getString("partnerid");
                    req.prepayId = json.getString("prepayid");
                    req.nonceStr = json.getString("noncestr");
                    req.timeStamp = json.getString("timestamp");
                    req.packageValue = json.getString("package");
                    req.sign = json.getString("sign");
                    // optional
                    req.extData = "app data";
                    Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);
                } else {
                    Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
                    Toast.makeText(PayActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.d("PAY_GET", "服务器请求错误");
                Toast.makeText(PayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("PAY_GET", "异常：" + e.getMessage());
            Toast.makeText(PayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}