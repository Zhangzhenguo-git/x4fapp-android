package com.x4fhuozhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubActivity extends BaseActivity {
    @BindView(R.id.iv_Return)
    ImageView ivReturn;
    @BindView(R.id.tv_HeadName)
    TextView tvHeadName;
    @BindView(R.id.iv_Setting)
    TextView ivSetting;
    @BindView(R.id.tv_MySub_Phone)
    EditText tvMySubPhone;
    @BindView(R.id.tv_MySub_Sms)
    TextView tvMySubSms;
    @BindView(R.id.tv_MySub_GetSms)
    TextView tvMySubGetSms;
    @BindView(R.id.et_MySub_Pwd)
    EditText etMySubPwd;
    @BindView(R.id.et_MySub_NewPwd)
    EditText etMySubNewPwd;
    @BindView(R.id.bt_MySub_Add)
    Button btMySubAdd;

    /**
     * 子账号数量
     */
    @Override
    public int loadView() {
        return R.layout.activity_sub;
    }

    @Override
    public void initView() {
        myClick();
    }

    @Override
    public void setOnListener() {

    }

    @Override
    public void initData() {

    }

    /**
     * 事件
     */
    private void myClick() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0, new Intent());
                finish();
            }
        });
    }
}
