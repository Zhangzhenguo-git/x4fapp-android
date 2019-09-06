package com.x4fhuozhu.app.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.adapter.MyFragmentPagerAdapter;
import com.x4fhuozhu.app.base.BaseActivity;
import com.x4fhuozhu.app.base.LazyLoadFragment;
import com.x4fhuozhu.app.fragment.home_fragment.GoodsFragment;
import com.x4fhuozhu.app.fragment.home_fragment.LFACFragment;
import com.x4fhuozhu.app.fragment.home_fragment.LookingCarFragment;
import com.x4fhuozhu.app.fragment.home_fragment.MonitoringFragment;
import com.x4fhuozhu.app.fragment.home_fragment.MyFragment;
import com.x4fhuozhu.app.fragment.home_fragment.OilPriceFragment;
import com.x4fhuozhu.app.fragment.home_fragment.OrderFragment;
import com.x4fhuozhu.app.fragment.home_fragment.SendOutFragment;
import com.x4fhuozhu.app.fragment.home_fragment.StrokeFragment;
import com.x4fhuozhu.app.fragment.home_fragment.WaybillFragment;
import com.x4fhuozhu.app.util.ToastUtils;
import com.x4fhuozhu.app.util.view_pager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.radio0)
    RadioButton radio0;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.radio4)
    RadioButton radio4;
    @BindView(R.id.mRadioGroupId)
    RadioGroup radioGroup;
    @BindView(R.id.radio5)
    RadioButton radio5;
    @BindView(R.id.radio6)
    RadioButton radio6;
    @BindView(R.id.radio7)
    RadioButton radio7;
    @BindView(R.id.radio8)
    RadioButton radio8;
    @BindView(R.id.radio9)
    RadioButton radio9;

    private List<LazyLoadFragment> allFragment;

    @Override
    public int loadView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        viewPager.setNoScroll(true);
        initSize();
        radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.radio0:
                    viewPager.setCurrentItem(0, true);
                    allFragment.get(0).refreshData();
                    break;
                case R.id.radio1:
                    viewPager.setCurrentItem(1, true);
                    allFragment.get(1).refreshData();
                    break;
                case R.id.radio2:
                    viewPager.setCurrentItem(2, true);
                    allFragment.get(2).refreshData();
                    break;
                case R.id.radio3:
                    viewPager.setCurrentItem(3, true);
                    allFragment.get(3).refreshData();
                    break;
                case R.id.radio4:
                    viewPager.setCurrentItem(4, true);
                    allFragment.get(4).refreshData();
                    break;
                case R.id.radio5:
                    viewPager.setCurrentItem(5, true);
                    allFragment.get(5).refreshData();
                    break;
                case R.id.radio6:
                    viewPager.setCurrentItem(6, true);
                    allFragment.get(6).refreshData();
                    break;
                case R.id.radio7:
                    viewPager.setCurrentItem(7, true);
                    allFragment.get(7).refreshData();
                    break;
                case R.id.radio8:
                    viewPager.setCurrentItem(8, true);
                    allFragment.get(8).refreshData();
                    break;
                case R.id.radio9:
                    viewPager.setCurrentItem(9, true);
                    allFragment.get(9).refreshData();
                    break;
            }
        });

    }

    //初始化按钮大小
    private void initSize() {
        RadioButton rab[] = {radio0, radio1, radio2, radio3, radio4, radio5, radio6, radio7, radio8, radio9};
        for (RadioButton radioButton : rab) {
            Drawable drs[] = radioButton.getCompoundDrawables();
            int w = (int) (drs[1].getMinimumWidth() * 2 / 2.1);
            int h = (int) (drs[1].getMinimumHeight() * 2 / 2.1);
            Rect r = new Rect(0, 0, w, h);
            drs[1].setBounds(r);
            radioButton.setCompoundDrawables(null, drs[1], null, null);
        }
    }

    @Override
    public void setOnListener() {

    }

    /**
     * 根据权限判断，要显示底部哪个按钮
     * 例如：
     * if（权限值==get）{
     * radio1.setVisibility(View.VISIBLE);
     * }
     * View.VISIBLE代表显示
     * View.GONE代表隐藏并不占空间
     */
    @Override
    public void initData() {
//        String nameType="货主会员";
//        String nameType="司机会员";
//        String nameType="登录1";
        String nameType="登录2";

        //显示的意思
        if(nameType.equals("货主会员")){
            radio4.setVisibility(View.VISIBLE);
            radio7.setVisibility(View.VISIBLE);
            radio8.setVisibility(View.VISIBLE);
            radio6.setVisibility(View.VISIBLE);
            radio9.setVisibility(View.VISIBLE);

            radio0.setVisibility(View.GONE);
            radio1.setVisibility(View.GONE);
            radio2.setVisibility(View.GONE);
            radio3.setVisibility(View.GONE);
            radio5.setVisibility(View.GONE);
        }else if(nameType.equals("司机会员")){
            radio4.setVisibility(View.VISIBLE);
            radio5.setVisibility(View.VISIBLE);
            radio6.setVisibility(View.VISIBLE);
            radio9.setVisibility(View.VISIBLE);

            radio0.setVisibility(View.GONE);
            radio1.setVisibility(View.GONE);
            radio2.setVisibility(View.GONE);
            radio3.setVisibility(View.GONE);
            radio7.setVisibility(View.GONE);
            radio8.setVisibility(View.GONE);
        }else if(nameType.equals("登录1")){
            radio3.setVisibility(View.VISIBLE);
            radio9.setVisibility(View.VISIBLE);

            radio0.setVisibility(View.GONE);
            radio1.setVisibility(View.GONE);
            radio2.setVisibility(View.GONE);
            radio4.setVisibility(View.GONE);
            radio5.setVisibility(View.GONE);
            radio6.setVisibility(View.GONE);
            radio7.setVisibility(View.GONE);
            radio8.setVisibility(View.GONE);
        }else if(nameType.equals("登录2")){
            radio0.setVisibility(View.VISIBLE);
            radio1.setVisibility(View.VISIBLE);
            radio2.setVisibility(View.VISIBLE);
            radio9.setVisibility(View.VISIBLE);


            radio3.setVisibility(View.GONE);
            radio4.setVisibility(View.GONE);
            radio5.setVisibility(View.GONE);
            radio6.setVisibility(View.GONE);
            radio7.setVisibility(View.GONE);
            radio8.setVisibility(View.GONE);
        }

        allFragment = new ArrayList<>();
        //找公司
        LFACFragment lfac = LFACFragment.newInstance(0);
        allFragment.add(lfac);
        radio0.setText("找公司");

        //监控
        MonitoringFragment monitoring = MonitoringFragment.newInstance(1);
        allFragment.add(monitoring);
        radio1.setText("监控");

        //订单
        OrderFragment order = OrderFragment.newInstance(2);
        allFragment.add(order);
        radio2.setText("订单");
        //油价
        OilPriceFragment oilPrice = OilPriceFragment.newInstance(3);
        allFragment.add(oilPrice);
        radio3.setText("油价");
        //找货
        GoodsFragment goods = GoodsFragment.newInstance(4);
        allFragment.add(goods);
        radio4.setText("找货");

        //行程
        StrokeFragment stroke = StrokeFragment.newInstance(5);
        allFragment.add(stroke);
        radio5.setText("行程");

        //运单
        WaybillFragment waybill = WaybillFragment.newInstance(6);
        allFragment.add(waybill);
        radio6.setText("运单");

        //找车
        LookingCarFragment lookcar = LookingCarFragment.newInstance(7);
        allFragment.add(lookcar);
        radio7.setText("找车");

        //发货
        SendOutFragment sendOut = SendOutFragment.newInstance(8);
        allFragment.add(sendOut);
        radio8.setText("发货");

        //我的
        MyFragment my = MyFragment.newInstance(9);
        allFragment.add(my);
        radio9.setText("我的");

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), allFragment, null));
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.radio0);
                break;
            case 1:
                radioGroup.check(R.id.radio1);
                break;
            case 2:
                radioGroup.check(R.id.radio2);
                break;
            case 3:
                radioGroup.check(R.id.radio3);
                break;
            case 4:
                radioGroup.check(R.id.radio4);
                break;
            case 5:
                radioGroup.check(R.id.radio5);
                break;
            case 6:
                radioGroup.check(R.id.radio6);
                break;
            case 7:
                radioGroup.check(R.id.radio7);
                break;
            case 8:
                radioGroup.check(R.id.radio8);
                break;
            case 9:
                radioGroup.check(R.id.radio9);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 获取权限使用的 RequestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1002;

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    }, PERMISSIONS_REQUEST_CODE);

        }

    }

    private static final int BACK_EXIT_DURATION = 2000;
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        //翻转屏
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int currentOrientation = getResources().getConfiguration().orientation;
            // 退出Activity时动画
            if (System.currentTimeMillis() - exitTime > BACK_EXIT_DURATION) {
                ToastUtils.makeToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
