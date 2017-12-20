package com.example.demo;

import android.os.Bundle;

import com.example.demo.base.BaseActivity;
import com.example.demo.base.BasePersenter;

public class FragmentActivity extends BaseActivity {

    @Override
    protected BasePersenter newP() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setToolbar(null, false);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), PageFragment.newInstance(10000), "")
                .commit();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
