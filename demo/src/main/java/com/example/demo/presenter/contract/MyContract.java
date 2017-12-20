package com.example.demo.presenter.contract;

import com.example.demo.base.LoadStatePersenter;
import com.example.demo.base.LoadStateView;

/**
 * Created by Pen on 2017/11/3.
 */

public interface MyContract {
    interface View extends LoadStateView<Presenter> {

    }

    interface Presenter extends LoadStatePersenter<View> {

    }
}
