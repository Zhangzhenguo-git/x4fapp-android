package com.x4fhuozhu.app.fragment.home_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.base.LazyLoadFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends LazyLoadFragment {
    Unbinder unbinder;
    @BindView(R.id.tvOrder)
    TextView tvOrder;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(View view) {
       tvOrder .setText("订单");
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
