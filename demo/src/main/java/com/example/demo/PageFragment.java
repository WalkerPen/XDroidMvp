package com.example.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.demo.base.BasePersenter;
import com.example.demo.base.LoadStateLazyFragment;

import cn.droidlover.xdroidmvp.mvp.LazyFragment;

/**
 * Created by Pen on 2017/11/21.
 */

public class PageFragment extends LoadStateLazyFragment {

    /*@Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        final TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(getArguments().getInt("title") + "");
        textView.setTextSize(22);

        Log.d("测试", "Fragment1 ------ onCreateView");
        setContentView(textView);

        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("加载完成");
            }
        }, 3000);
    }*/



    @Override
    protected BasePersenter newP() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        final TextView textView = new TextView(getContext());
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
        }, 3000);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public static PageFragment newInstance(int position) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("title", position);
        //fragment保存参数，传入一个Bundle对象
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
}
