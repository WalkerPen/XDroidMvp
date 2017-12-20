package com.example.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.demo.base.LoadStateActivity;
import com.example.demo.base.LoadStatePersenter;

import butterknife.BindView;

public class XmlActivity extends LoadStateActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected LoadStatePersenter newP() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("LazyFragment");
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xml;
    }

    @Override
    protected boolean immersion() {
        return true;
    }

    private class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
