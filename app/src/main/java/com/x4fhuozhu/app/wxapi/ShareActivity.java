package com.x4fhuozhu.app.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.common.BaseConstant;

public class ShareActivity extends Activity {
    private IWXAPI api;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, BaseConstant.WXAPP_ID, false);
        sharePage();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ShareActivity.class);
        context.startActivity(intent);
    }

    public void sharePage() {
        Intent intent = getIntent();
        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.baidu.com";

        //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "行四方微信分享 ";
        msg.description = "这里是网页描述，有效期1个月！";
        Bitmap thumbBmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_share);
        msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true);
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetScene;
        // req.userOpenId = getOpenId();
        //调用api接口，发送数据到微信
        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
