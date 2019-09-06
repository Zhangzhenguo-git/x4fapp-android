package com.x4fhuozhu.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.x4fhuozhu.app.activity.HomeActivity;
import com.x4fhuozhu.app.common.BaseConstant;
import com.x4fhuozhu.app.http.subscribe.LoginSubscribe;
import com.x4fhuozhu.app.http.subscribe.PostSubscribe;
import com.x4fhuozhu.app.http.util.LogUtil;
import com.x4fhuozhu.app.http.util.OnSuccessAndFailureSubscribe;
import com.x4fhuozhu.app.http.util.OnSuccessAndFailureListener;
import com.x4fhuozhu.app.http.util.RetrofitFactory;
import com.x4fhuozhu.app.kit.RegExpKit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class LoginActivity extends Activity {
    @BindView(R.id.topbar) QMUITopBar mTopBar;
    @BindView(R.id.reg) TextView regText;
    @BindView(R.id.phone) EditText phoneInput;
    @BindView(R.id.sms_code) EditText smsCodeInput;
    @BindView(R.id.send_sms) QMUIRoundButton sendSmsButton;
    @BindView(R.id.submit) QMUIRoundButton submitButton;


    private int pageNumber = 0;
    private int userId = 20;
    final String TAG = getClass().getSimpleName();
    final static String KEY_PHONE_NUMBER = "phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        ButterKnife.bind(this, root);
        //初始化状态栏
        initTopBar();
        setContentView(root);
        SharedPreferences sp = getSharedPreferences(BaseConstant.LOGIN, Context.MODE_PRIVATE);
        String phoneNum = sp.getString("phone", "");
        //设置默认登录号码
        if (RegExpKit.isPhone(phoneNum)) {
            phoneInput.setText(phoneNum);
        }
    }

    public static void start(Context context, String phoneNumber) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(KEY_PHONE_NUMBER, phoneNumber);
        context.startActivity(intent);
    }

    /**
     * 请求数据
     */
    @OnClick(R.id.submit)
    public void loginSubmit() {
        LoginSubscribe.doLogin(phoneInput.getText().toString(), smsCodeInput.getText().toString(), new OnSuccessAndFailureSubscribe(new OnSuccessAndFailureListener() {
            @Override
            public void onSuccess(String result) {
                //成功
                LogUtil.e(result);
                JSONObject json = JSONObject.parseObject(result);
                saveLoginData(json);
                Toast.makeText(LoginActivity.this, "请求成功：", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMsg) {
                //失败
                Toast.makeText(LoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, this));
    }

    /**
     * 获取短信验证码
     */
    private void getSms() {
        submitButton.setClickable(false);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phoneInput.getText().toString());
        PostSubscribe.post("user/sendSms", map, new OnSuccessAndFailureSubscribe(new OnSuccessAndFailureListener() {
            @Override
            public void onSuccess(String result) {
                JSONObject json = JSONObject.parseObject(result);
                Log.e("获取的短信", result);
                if (!json.getString("state").equals("ok")) {
                    Toast.makeText(LoginActivity.this, json.getString("msg"), Toast.LENGTH_LONG).show();
                }
                submitButton.setClickable(true);
            }

            @Override
            public void onFailure(String errorMsg) {
                //失败
                Toast.makeText(LoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, this));
    }


    /**
     * 保存登录信息
     *
     * @param data
     */
    private void saveLoginData(JSONObject data) {
//        JSONObject json = data.getJSONObject("user");
//        LogUtil.d(json.toString());
//        SharedPreferences sp = getSharedPreferences(BaseConstant.LOGIN, Context.MODE_PRIVATE);
//        //私有数据Editor
//        SharedPreferences.Editor editor = sp.edit();
//        //获取编辑器
//        editor.putString("token", json.getString("token"));
//        editor.putString("name", json.getString("name"));
//        editor.putString("type", json.getString("type"));
//        editor.putString("phone", json.getString("phone"));
//        editor.apply();
//        RetrofitFactory.getInstance().setAccessToken(json.getString("token"));

        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
        startActivityForResult(intent,1);
    }

    @OnClick(R.id.send_sms)
    public void sendSms(View view) {
        String phoneNo = phoneInput.getText().toString();
        if (!RegExpKit.isPhone(phoneNo)) {
            sendSmsButton.setText("号码错误");
            return;
        }
        getSms();
        final long codeTimes = 120;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(codeTimes - 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return codeTimes - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        sendSmsButton.setEnabled(false);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        sendSmsButton.setText("剩余" + value + "秒");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        sendSmsButton.setEnabled(true);
                        sendSmsButton.setText("获取验证码");
                    }
                });
    }

    @OnClick(R.id.reg)
    public void goReg() {
        RegActivity.start(LoginActivity.this, phoneInput.getText().toString());
        finish();
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopBar.addRightTextButton("注册", 1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegActivity.start(LoginActivity.this, "reg");
                finish();
            }
        });
        mTopBar.setTitle("登录行四方");
    }

}
