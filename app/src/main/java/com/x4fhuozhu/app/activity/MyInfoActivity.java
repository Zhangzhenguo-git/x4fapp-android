package com.x4fhuozhu.app.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.iv_Return)
    ImageView ivReturn;
    @BindView(R.id.tv_HeadName)
    TextView tvHeadName;
    @BindView(R.id.iv_Setting)
    TextView ivSetting;
    @BindView(R.id.tv_MyInfo_Gsmc)
    TextView tvMyInfoGsmc;
    @BindView(R.id.tv_MyInfo_Sqsyrxm)
    TextView tvMyInfoSqsyrxm;
    @BindView(R.id.tv_MyInfo_Sjh)
    TextView tvMyInfoSjh;
    @BindView(R.id.tv_MyInfo_Sftbye)
    TextView tvMyInfoSftbye;
    @BindView(R.id.tv_MyInfo_Hyyxq)
    TextView tvMyInfoHyyxq;
    @BindView(R.id.tv_MyInfo_Zzhsl)
    TextView tvMyInfoZzhsl;
    @BindView(R.id.tv_MyInfo_Yhdkyz)
    TextView tvMyInfoYhdkyz;
    @BindView(R.id.tv_MyInfo_Appbbh)
    TextView tvMyInfoAppbbh;
    @BindView(R.id.tv_MyInfo_Gyxsf)
    TextView tvMyInfoGyxsf;
    @BindView(R.id.bt_MyInfo_ReturnLogin)
    Button btMyInfoReturnLogin;
    @BindView(R.id.ll_MyInfo_Sqsyrxm)
    LinearLayout llMyInfoSqsyrxm;
    @BindView(R.id.ll_MyInfo_Sftbye)
    LinearLayout llMyInfoSftbye;
    @BindView(R.id.ll_MyInfo_Hyyxq)
    LinearLayout llMyInfoHyyxq;
    @BindView(R.id.ll_MyInfo_Zzhsl)
    LinearLayout llMyInfoZzhsl;
    @BindView(R.id.ll_MyInfo_Yhdkyz)
    LinearLayout llMyInfoYhdkyz;
    @BindView(R.id.ll_MyInfo_Appbbh)
    LinearLayout llMyInfoAppbbh;
    @BindView(R.id.ll_MyInfo_Gyxsf)
    LinearLayout llMyInfoGyxsf;

    @Override
    public int loadView() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        ivReturn.setVisibility(View.VISIBLE);
        tvHeadName.setText("我的信息");
        ivSetting.setVisibility(View.GONE);
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
                setResult(0,new Intent());
                finish();
            }
        });
        llMyInfoSqsyrxm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,IdentityActivity.class);
                startActivityForResult(intent,1);
            }
        });
        llMyInfoSftbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,BalanceActivity.class);
                startActivityForResult(intent,1);
            }
        });
        llMyInfoHyyxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,MemberActivity.class);
                startActivityForResult(intent,1);
            }
        });
        llMyInfoZzhsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,SubActivity.class);
                startActivityForResult(intent,1);
            }
        });
        llMyInfoYhdkyz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,BankVerActivity.class);
                startActivityForResult(intent,1);
            }
        });
        llMyInfoAppbbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MyInfoActivity.this)
                        .setTitle("系统升级提醒")
                        .setMessage("未发现新版本")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        llMyInfoGyxsf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,AboutActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }

    /**
     * 设置字体图转换为图片
     *
     * @param tvTitle
     * @param tvIcon
     * @param id
     */
    private void setTextTypeFace(String tvTitle, TextView tvName, TextView tvIcon, int id, boolean isTrue) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        TextView tvAllIcon = tvIcon;
        tvAllIcon.setTypeface(typeface);
        if (isTrue) {
            TextView tvAllTitle = tvName;
            tvAllTitle.setText(tvTitle);
        }
        tvAllIcon.setText(getResources().getString(id));
        tvAllIcon.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tvAllIcon.setTextSize(getResources().getDimension(R.dimen.sp_13));

    }
}
