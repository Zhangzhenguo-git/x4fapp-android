package com.x4fhuozhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.iv_Return)
    ImageView ivReturn;
    @BindView(R.id.tv_HeadName)
    TextView tvHeadName;
    @BindView(R.id.iv_Setting)
    TextView ivSetting;
    @BindView(R.id.iv_About_Log)
    ImageView ivAboutLog;
    @BindView(R.id.tv_About_CompanyName)
    TextView tvAboutCompanyName;
    @BindView(R.id.tv_About_Tagging)
    TextView tvAboutTagging;

    /**
     * 关于
     */

    @Override
    public int loadView() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        ivReturn.setVisibility(View.VISIBLE);
        tvHeadName.setText("关于行四方");
        ivSetting.setVisibility(View.GONE);
        toRoundCorner(R.mipmap.ic_launcher, R.drawable.logo, ivAboutLog);
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
    /**
     * 修改圆角图片
     *
     * @param id
     * @return
     */
    private void toRoundCorner(int id, int newId, ImageView view) {
        RequestOptions options = RequestOptions.circleCropTransform()
                //加载失败后显示的图片
                .error(id)
                .centerCrop()
                //设置圆形
                .circleCrop()
                //是否跳转缓存策略
                .skipMemoryCache(true)
                //仅显示缓存后的图片，降低分辨率或转换后的
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片优先级
                .priority(Priority.HIGH);
        Glide.with(this).load(newId).apply(options).into(view);


    }

}
