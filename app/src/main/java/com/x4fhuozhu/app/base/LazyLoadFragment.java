package com.x4fhuozhu.app.base;

import android.content.Intent;
import android.os.Bundle;

import io.reactivex.disposables.Disposable;

/**
 * Created by jin on 2016/12/27.
 */

public abstract class LazyLoadFragment<T extends IBasePresenter> extends BaseFragment<T> {

    /**
     * 是否初始化过布局
     */
    public boolean isViewInitiated;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated;
    protected Disposable disposable;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData();
        }else {
            noFetchData();
        }
    }

    /**
     * 不加载
     *
     */
    protected abstract void noFetchData();

    /**
     * 懒加载
     */
    public abstract void fetchData();

    public void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     * 判断懒加载条件
     *
     * @param forceUpdate 强制更新，好像没什么用？
     */
    public void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unsubscribe();
//    }
//
//    protected void unsubscribe() {
//        if (disposable != null && !disposable.isDisposed()) {
//            disposable.dispose();
//        }
//    }

    public void openActivity(Class<?> cls) {
        openActivity(cls, null);
    }

    public void openActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    //刷新数据
    public void refreshData (){}
}