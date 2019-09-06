package com.x4fhuozhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceActivity extends BaseActivity {
    @BindView(R.id.iv_Return)
    ImageView ivReturn;
    @BindView(R.id.tv_HeadName)
    TextView tvHeadName;
    @BindView(R.id.iv_Setting)
    TextView ivSetting;
    @BindView(R.id.tv_About_CompanyName)
    TextView tvAboutCompanyName;
    @BindView(R.id.tv_About_Tagging)
    TextView tvAboutTagging;
    @BindView(R.id.ll_Balance_Bottom_Layout)
    LinearLayout llBalanceBottomLayout;
    @BindView(R.id.tv_Balance_MoneyIcon)
    TextView tvBalanceMoneyIcon;
    @BindView(R.id.tv_Balance_Money)
    TextView tvBalanceMoney;
    @BindView(R.id.bt_Balance_Recharge)
    Button btBalanceRecharge;
    @BindView(R.id.bt_MyInfo_OilUnion)
    Button btMyInfoOilUnion;
    @BindView(R.id.bt_MyInfo_Cash)
    Button btMyInfoCash;
    @BindView(R.id.ll_Balance_Center_Layout)
    LinearLayout llBalanceCenterLayout;

    /**
     * 四方通宝余额
     */

    @Override
    public int loadView() {
        return R.layout.activity_balance;
    }

    @Override
    public void initView() {
        ivReturn.setVisibility(View.VISIBLE);
//        tvHeadName.setText("关于行四方");
        tvHeadName.setVisibility(View.GONE);
        ivSetting.setVisibility(View.VISIBLE);
        ivSetting.setText("交易明细");
        ivSetting.setTextColor(getResources().getColor(R.color.white));
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
    private void myClick(){
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0,new Intent());
                finish();
            }
        });
    }

}
