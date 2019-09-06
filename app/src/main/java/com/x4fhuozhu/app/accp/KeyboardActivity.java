package com.x4fhuozhu.app.accp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.x4fhuozhu.app.R;

import cn.passguard.PassGuardEdit;

public class KeyboardActivity extends Activity implements View.OnClickListener{
    private View mLayoutContent;
    private PassGuardEdit mEtPwd = null;
    private Button mBtGetPwd;
    private TextView mTvPwdInfo;
/*
* "ret_code":"0000","rsa_public_content":"308189028181009017e40c4b053ef663903066618131e705a91641441d8bc5de082a12147559dcbc790e7b85a0e42a388effa2b7d0c5f734733ef14869fb735b5245bda1d79e32c35004288190e080f8abe8ae7c8f2ff81738c1c32d6e944be7ad1197cb1d8c76455d6da8d623a0513647f5a59d1e9aa6ca077f136040b550ad411728455bf6230203010001","map_arr":"WycxMDUnLCAnNTknLCAnNDMnLCAnNTUnLCAnNDYnLCAnNjMnLCAnODInLCAnMTE1JywgJzQ0JywgJzk0JywgJzgzJywgJzEwMCcsICc1OCcsICcxMTgnLCAnMTExJywgJzM1JywgJzUyJywgJzEyNScsICc0MCcsICcxMDknLCAnMTIwJywgJzQ1JywgJzc5JywgJzM4JywgJzEyMycsICcxMTcnLCAnMzQnLCAnMTE0JywgJzcwJywgJzUxJywgJzExMicsICczMycsICcxMjInLCAnNzInLCAnOTknLCAnNjInLCAnOTcnLCAnNzMnLCAnNDgnLCAnNjQnLCAnODgnLCAnMTIxJywgJzgxJywgJzU3JywgJzM3JywgJzg5JywgJzg0JywgJzY3JywgJzExMycsICc2NScsICc2OScsICcxMDgnLCAnNzcnLCAnNzEnLCAnNjAnLCAnNjEnLCAnODcnLCAnOTUnLCAnMzYnLCAnMTI0JywgJzQxJywgJzc4JywgJzEwNCcsICc3NicsICcxMTYnLCAnNjYnLCAnMTAzJywgJzkyJywgJzc0JywgJzU0JywgJzg1JywgJzU2JywgJzExMCcsICc5MScsICc0OScsICcxMDEnLCAnMTE5JywgJzg2JywgJzk2JywgJzgwJywgJzkwJywgJzkzJywgJzY4JywgJzEyNicsICcxMDInLCAnMTA2JywgJzUwJywgJzEwNycsICc5OCcsICc3NScsICc0NycsICczOScsICc1MycsICc0Midd","ret_msg":"交易成功","random_key":"7cb0ecc4-1cd1-46f4-b1bd-17a764aa3459","ok":true,"random_value":"78268949972425754815085914116473","user_id":"11","license":"UitnQTArYzhodDBPdVVGTERYV3VaTWs4REd0NW5kNWwrbmxSMFJ5UTRHVnhGUCs5NnJ0UEhWQ0NBL1U0VWJsVEZjMVNoMUp6TWd5NHFDVUE1UFdIeFRkUVM3RzdDRGlybkJVTE84NGF4ODlUNm9HdkEzUzFHVkI0QVZpNFZUS21TWDltTVgzNW1scnJZM243aDViNlpCRmg4MWpZS2FLdis0ditkVGlkTHR3PXsiaWQiOjAsInR5cGUiOiJwcm9kdWN0IiwicGFja2FnZSI6WyIiXSwiYXBwbHluYW1lIjpbIioueDRmYW5nLmNvbSJdLCJwbGF0Zm9ybSI6MTB9"} */
    private static final String KEYBOARD_LICENSE = "aHBOUHdvbXJZK1RweHB4WlFIdnExbVhIajZnREQyaC80K3FVSDh0am9uMkRlaWJ4OHp5bVptYnhPcXRDbVBjRzhOY3RMTGUrcXNSWWJMbU5Cd0Q0bHFteFpVajVITnJFT1hGQlVOdzJjSnhBT2FpbmRETVNyTmd1SUFTbmJ1MUpES3U4SzNWUHZ2VmhuMHp0WnhmMit5QWY1NWtlL1ZaelVnKzlYUWdCNWpvPXsiaWQiOjAsInR5cGUiOiJ0ZXN0IiwicGxhdGZvcm0iOjIsIm5vdGJlZm9yZSI6IjIwMTgxMTIwIiwibm90YWZ0ZXIiOiIyMDE5MDIyMCJ9";

    static {
        System.loadLibrary("PassGuard");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accp_keyboard_activity_main);

        initView();
        initData();
        initEvent();
    }

    private void initView(){
        mEtPwd = findViewById(R.id.psdEditText);
        mLayoutContent = findViewById(R.id.layout_content);
        mBtGetPwd = findViewById(R.id.bt_get_cryptPwd);
        mTvPwdInfo = findViewById(R.id.tv_pwd_info);
    }

    private void initData(){
        initPassGuard(mEtPwd, "testkey", mLayoutContent);
    }

    private void initEvent(){
        mBtGetPwd.setOnClickListener(this);
    }

    private void initPassGuard(PassGuardEdit edit, String key, View scrollView) {
        PassGuardEdit.setLicense(KEYBOARD_LICENSE);
        edit.setCipherKey(key);
        edit.setPublicKey("3081890281810092d9d8d04fb5f8ef9b8374f21690fd46fdbf49b40eeccdf416b4e2ac2044b0cfe3bd67eb4416b26fd18c9d3833770a526fd1ab66a83ed969af74238d6c900403fc498154ec74eaf420e7338675cad7f19332b4a56be4ff946b662a3c2d217efbe4dc646fb742b8c62bfe8e25fd5dc59e7540695fa8b9cd5bfd9f92dfad009d230203010001");
        edit.setMaxLength(6);
        edit.setClip(false);
        edit.setButtonPress(false);
        edit.setButtonPressAnim(false);
        edit.setWatchOutside(false);
        edit.useNumberPad(true);
        edit.setInputRegex("[a-zA-Z0-9@_\\.]");
        //设置这个后，键盘显示时，若会遮挡编辑框，则scrollView会上推，使不挡住输入框
        if (null != scrollView){
            edit.needScrollView(true);
            edit.setScrollView(scrollView);
        }

        edit.initPassGuardKeyBoard();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_get_cryptPwd){
            int pwdLength = mEtPwd.getLength();
            String pwdMD5 = mEtPwd.getMD5();
            String pwdCrypt = mEtPwd.getRSAAESCiphertext();
            String text = "密码长度：" + pwdLength + "\n"
                    + "MD5值：" + pwdMD5 + "\n"
                    + "密码密文：" + pwdCrypt;
            mTvPwdInfo.setText(text);
        }
    }
}
