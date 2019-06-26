package com.wangchao.mywork.utils.adapterrelation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> res;
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> res) {
        super(fm);
        this.res = res;
    }

    @Override
    public Fragment getItem(int i) {
        return res.get(i);
    }


    @Override
    public int getCount() {
        return res.size();
    }
}