package com.x4fhuozhu.app.fragment.home_fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.activity.MyInfoActivity;
import com.x4fhuozhu.app.adapter.MyMenuAdapter;
import com.x4fhuozhu.app.base.LazyLoadFragment;
import com.x4fhuozhu.app.bean.GetMyMenuData;
import com.x4fhuozhu.app.bean.ModelMenuItem;
import com.x4fhuozhu.app.util.RoundedCornersTransformation;
import com.x4fhuozhu.app.util.RxRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyFragment extends LazyLoadFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_Return)
    ImageView ivReturn;
    @BindView(R.id.iv_Setting)
    TextView ivSetting;
    @BindView(R.id.iv_MyIcon)
    ImageView ivMyIcon;
    @BindView(R.id.tv_MyInfo_UserName)
    TextView tvMyInfoUserName;
    @BindView(R.id.tv_MyInfo_UserPhone)
    TextView tvMyInfoUserPhone;
    @BindView(R.id.tv_MyInfo_UserAddress)
    TextView tvMyInfoUserAddress;
    @BindView(R.id.tv_MyInfo_UserType)
    TextView tvMyInfoUserType;
    @BindView(R.id.tv_HeadName)
    TextView tvHeadName;
    @BindView(R.id.tv_My_O_Icon)
    ImageView tvMyOIcon;
    @BindView(R.id.tv_My_O_Title)
    TextView tvMyOTitle;
    @BindView(R.id.tv_My_T_Icon)
    TextView tvMyTIcon;
    @BindView(R.id.tv_My_T_Title)
    TextView tvMyTTitle;
    @BindView(R.id.tv_My_F_Icon)
    TextView tvMyFIcon;
    @BindView(R.id.tv_My_F_Title)
    TextView tvMyFTitle;
    @BindView(R.id.my_ListView)
    RecyclerView myListView;
    @BindView(R.id.tv_My_Type_Jurisdiction)
    TextView tvMyTypeJurisdiction;
    @BindView(R.id.tv_My_Type_NewJurisdiction)
    TextView tvMyTypeNewJurisdiction;
    @BindView(R.id.ll_MyType_Layout)
    LinearLayout llMyTypeLayout;
    private List<ModelMenuItem> mData;
    private MyMenuAdapter myMenuAdapter;
    //每排显示数量
    private int mColumnCount = 3;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {

        tvHeadName.setText("会员中心");

        ivSetting.setVisibility(View.VISIBLE);
        setTextTypeFace("",ivSetting,ivSetting,R.string.sz,false);
        ivSetting.setTextColor(getResources().getColor(R.color.white));
        ivSetting.setTextSize(getResources().getDimension(R.dimen.dp_8));

        tvMyInfoUserName.setText("于小莲");
        tvMyInfoUserPhone.setText("19866666666");
        tvMyInfoUserAddress.setText("中石化黄岛第88站");
        tvMyInfoUserType.setText("登录12");
    }

    /**
     * 设置字体图转换为图片
     *
     * @param tvTitle
     * @param tvIcon
     * @param id
     */
    private void setTextTypeFace(String tvTitle, TextView tvName, TextView tvIcon, int id,boolean isTrue) {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font.ttf");
        TextView tvAllIcon = tvIcon;
        tvAllIcon.setTypeface(typeface);
        if (isTrue){
            TextView tvAllTitle=tvName;
            tvAllTitle.setText(tvTitle);
        }
        tvAllIcon.setText(getResources().getString(id));
        tvAllIcon.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tvAllIcon.setTextSize(getResources().getDimension(R.dimen.sp_13));

    }

    @Override
    public void initData() {
        List<GetMyMenuData> listData = new ArrayList<>();
        addMenu(listData);
        myClick();
    }

    private void myClick(){
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), MyInfoActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void noFetchData() {

    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 修改圆角图片
     * @param id
     * @return
     */
    private void toRoundCorner(int id, int newId, ImageView view) {
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                //设置加载失败后的图片显示
                .error(id)
                .centerCrop()
                //圆形
                .circleCrop()
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(true)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH).circleCrop();
        Glide.with(mContext).load(newId).apply(mRequestOptions).into(view);
    }

    /**
     * 加载数据
     *
     * @param listData
     */
    public void addMenu(List<GetMyMenuData> listData) {
        GetMyMenuData item = new GetMyMenuData();
//        item.setModulecode("试用货主会员");
//        item.setModulecode("试用司机会员");
//        item.setModulecode("站员1");
        item.setModulecode("站员2");
        listData.add(item);
        mData = new ArrayList<>();
        for (GetMyMenuData dataBean : listData) {
            if (dataBean.getModulecode().equals("试用货主会员")) {
                //试用货主会员
                toRoundCorner(R.mipmap.ic_launcher, R.drawable.logo, ivMyIcon);
                tvMyTypeJurisdiction.setText("试用货主会员");
                tvMyTypeNewJurisdiction.setText("升级为付费会员");
                //判断当前账号是否会员，然后进行显示和隐藏
//                llMyTypeLayout.setVisibility(View.VISIBLE);
//                llMyTypeLayout.setVisibility(View.GONE);
                setTextTypeFace("运单",tvMyTTitle,tvMyTIcon, R.string.yd,true);
                setTextTypeFace("货物",tvMyFTitle ,tvMyFIcon, R.string.zh,true);

                mData.add(new ModelMenuItem("加油站", R.string.jyz));
                mData.add(new ModelMenuItem("实体加油卡", R.string.jyk));
                mData.add(new ModelMenuItem("子账号管理", R.string.zzh));
                mData.add(new ModelMenuItem("道路服务", R.string.dlfw));
                mData.add(new ModelMenuItem("ETC", R.string.etc));
                mData.add(new ModelMenuItem("特来电", R.string.tld));
                mData.add(new ModelMenuItem("货物跟踪权限", R.string.hwgzqx));
                mData.add(new ModelMenuItem("评价管理", R.string.pj));
            } else if (dataBean.getModulecode().equals("试用司机会员")) {
                //试用司机会员
                //判断当前账号是否会员，然后进行显示和隐藏
//                llMyTypeLayout.setVisibility(View.VISIBLE);
//                llMyTypeLayout.setVisibility(View.GONE);
                toRoundCorner(R.mipmap.ic_launcher, R.drawable.logo, ivMyIcon);
                tvMyTypeJurisdiction.setText("试用司机会员");
                tvMyTypeNewJurisdiction.setText("升级为付费会员");

                setTextTypeFace("运单",tvMyTTitle,tvMyTIcon, R.string.yd,true);
                setTextTypeFace("油卡",tvMyFTitle ,tvMyFIcon, R.string.jyk,true);

                mData.add(new ModelMenuItem("加油站", R.string.jyz));
                mData.add(new ModelMenuItem("车辆", R.string.cl));
                mData.add(new ModelMenuItem("线路", R.string.xl));
                mData.add(new ModelMenuItem("分享", R.string.fx));
                mData.add(new ModelMenuItem("评价管理", R.string.pj));
            } else if (dataBean.getModulecode().equals("站员1")) {
                //站员1
                //判断当前账号是否会员，然后进行显示和隐藏
//                llMyTypeLayout.setVisibility(View.VISIBLE);
//                llMyTypeLayout.setVisibility(View.GONE);
                toRoundCorner(R.mipmap.ic_launcher, R.drawable.logo, ivMyIcon);
                tvMyTypeJurisdiction.setText("站员1");
                tvMyTypeNewJurisdiction.setText("升级为付费会员");
//                setTextTypeFace("四方通宝", tvMyOIcon, R.string.sftb);

                setTextTypeFace("油价",tvMyTTitle,tvMyTIcon, R.string.yj,true);
                setTextTypeFace("增值税发票",tvMyFTitle ,tvMyFIcon, R.string.zzsfp,true);
                mData.add(new ModelMenuItem("加油站位置", R.string.jyzwz));
            } else if (dataBean.getModulecode().equals("站员2")) {
                //站员2
                //判断当前账号是否会员，然后进行显示和隐藏
//                llMyTypeLayout.setVisibility(View.VISIBLE);
                llMyTypeLayout.setVisibility(View.GONE);
                toRoundCorner(R.mipmap.ic_launcher, R.drawable.logo, ivMyIcon);
                tvMyTypeJurisdiction.setText("");
                tvMyTypeNewJurisdiction.setText("");
//                setTextTypeFace("四方通宝", tvMyOIcon, R.string.sftb);
                setTextTypeFace("订单",tvMyTTitle,tvMyTIcon, R.string.yd,true);
                setTextTypeFace("监控",tvMyFTitle ,tvMyFIcon, R.string.jk,true);
            }
            mData.add(new ModelMenuItem("消息", R.string.xx));
        }
        if (mColumnCount <= 1) {
            myListView.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            myListView.setLayoutManager(new GridLayoutManager(mContext, mColumnCount));
        }
        myListView.addItemDecoration(new RxRecyclerViewDivider(dp2px(mContext, 5f)));
        myMenuAdapter = new MyMenuAdapter(getActivity(), mData);
        myListView.setAdapter(myMenuAdapter);
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
