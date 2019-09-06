package com.x4fhuozhu.app.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by cgspine on 2018/1/7.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView<T> {
    protected T presenter;
    @NonNull
    protected Context mContext;
    private View view;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutId();

    /**
     * 初始化视图控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData() throws NullPointerException;

//    /**
//     * 初始化 Toolbar
//     */
//    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
////        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(presenter);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(attachLayoutId(), container, false);
            ButterKnife.bind(this, view);
            initView(view);
            initData();
        }

        return view;
    }

    /**
     * 绑定生命周期
     */
//    @Override
//    public <X> AutoDisposeConverter<X> bindAutoDispose() {
//        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
//                .from(this, Lifecycle.Event.ON_DESTROY));
//    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}
