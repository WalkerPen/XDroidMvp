package com.example.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.demo.base.LoadStateLazyFragment;
import com.example.demo.base.LoadStatePersenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by Pen on 2017/11/21.
 */

public class PageFragment extends LoadStateLazyFragment {

    @BindView(R.id.iv_header)
    ImageView mIvHeader;

    @Override
    protected LoadStatePersenter newP() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        /*final TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(getArguments().getInt("title") + "");
        textView.setTextSize(22);

        Log.d("测试", "Fragment1 ------ onCreateView");
        setContent(textView);

        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("加载完成");
            }
        }, 3000);*/
        ILFactory.getLoader().loadResource(mIvHeader, R.drawable.biting, null);
        addHeader(mIvHeader);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public static PageFragment newInstance(int position) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("title", position);
        //fragment保存参数，传入一个Bundle对象
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @OnClick(R.id.bt_request)
    public void onViewClicked() {
        showLoading();
    }
}
