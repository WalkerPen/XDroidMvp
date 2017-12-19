package com.example.demo.presenter.contract;

import com.example.demo.base.BasePersenter;
import com.example.demo.base.BaseView;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.IView;

/**
 * Created by Pen on 2017/11/3.
 */

public interface MyContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePersenter<View> {

    }
}
