package com.x4fhuozhu.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.x4fhuozhu.app.base.LazyLoadFragment;

import java.util.List;

/**
 * Created by jin on 2017/9/19.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<LazyLoadFragment> list;
    private String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm,
                                  List<LazyLoadFragment> list,
                                  String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        return super.instantiateItem(container, position);


    }
}
