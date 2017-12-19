package com.example.demo.presenter.contract;

import com.example.demo.base.BasePersenter;
import com.example.demo.base.BaseView;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.IView;

/**
 * Created by Pen on 2017/7/27.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePersenter<View> {
        void login(String phone, String password);
        void getProblem(String search, int type);
    }
}
