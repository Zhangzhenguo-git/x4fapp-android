package com.x4fhuozhu.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.x4fhuozhu.app.bmap.LocationActivity;

public class RegActivity extends Activity {
    @BindView(R.id.topbar) QMUITopBar mTopBar;
    @BindView(R.id.login)
    TextView login;
    final String TAG = getClass().getSimpleName();
    private static final String KEY_PHONE_NUMBER = "phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_reg, null);
        ButterKnife.bind(this, root);
        //初始化状态栏
        initTopBar();
        setContentView(root);
    }

    public static void start(Context context, String phoneNumber) {
        Intent intent = new Intent(context, RegActivity.class);
        intent.putExtra(KEY_PHONE_NUMBER, phoneNumber);
        context.startActivity(intent);
    }

    @OnClick(R.id.login)
    public void goLoing() {
        LoginActivity.start(RegActivity.this, "reg");
        finish();
    }

    private void initTopBar() {
        mTopBar.addRightTextButton("地图", 2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationActivity.start(RegActivity.this, "120","13");
                finish();
            }
        });
        mTopBar.setTitle("注册行四方账号");
    }
}
