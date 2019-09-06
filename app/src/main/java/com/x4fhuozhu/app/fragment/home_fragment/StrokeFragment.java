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

public class StrokeFragment extends LazyLoadFragment {
    Unbinder unbinder;
    @BindView(R.id.tvStrok)
    TextView tvStrok;

    public StrokeFragment() {
        // Required empty public constructor
    }

    public static StrokeFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        StrokeFragment fragment = new StrokeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_stroke;
    }

    @Override
    protected void initView(View view) {
        tvStrok.setText("行程");
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
