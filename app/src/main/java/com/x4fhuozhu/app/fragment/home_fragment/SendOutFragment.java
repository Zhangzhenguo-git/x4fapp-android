package com.x4fhuozhu.app.fragment.home_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.base.LazyLoadFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SendOutFragment extends LazyLoadFragment {
    Unbinder unbinder;
    @BindView(R.id.tvSendOut)
    TextView tvSendOut;

    public SendOutFragment() {
        // Required empty public constructor
    }

    public static SendOutFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SendOutFragment fragment = new SendOutFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_send_out;
    }

    @Override
    protected void initView(View view) {
        tvSendOut.setText("发货");
    }

    @Override
    public void initData() {

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
}
