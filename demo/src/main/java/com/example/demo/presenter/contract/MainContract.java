package com.example.demo.presenter.contract;

import com.example.demo.base.LoadStatePersenter;
import com.example.demo.base.LoadStateView;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.IView;

/**
 * Created by Pen on 2017/7/27.
 */

public interface MainContract {
    interface View extends LoadStateView<Presenter> {

    }

    interface Presenter extends LoadStatePersenter<View> {
        void login(String phone, String password);

        void getProblem(String search, int type);
    }
}
