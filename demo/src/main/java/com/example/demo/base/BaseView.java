package com.example.demo.base;

import cn.droidlover.xdroidmvp.mvp.IView;

/**
 * Created by Pen on 2017/12/20.
 */

public interface BaseView<P> extends IView<P> {

    void showToast();
}
