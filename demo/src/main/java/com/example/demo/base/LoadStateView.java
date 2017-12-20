package com.example.demo.base;

import cn.droidlover.xdroidmvp.mvp.IView;

/**
 * Created by Pen on 2017/10/17.
 */

public interface LoadStateView<P> extends IView<P>{
    void showLoading();

    void showContent();

    void showError();

    void showEmpty();
}
