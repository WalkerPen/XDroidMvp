package com.example.demo;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.demo.base.LoadStateFragment;
import com.example.demo.presenter.contract.MyContract;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by Pen on 2017/11/3.
 */

public class MyFragment extends LoadStateFragment {

    @BindView(R.id.iv_header)
    ImageView mIvHeader;

    @Override
    protected MyContract.Presenter newP() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ILFactory.getLoader().loadResource(mIvHeader, R.drawable.biting, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

}
