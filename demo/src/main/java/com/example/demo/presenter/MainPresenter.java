package com.example.demo.presenter;

import android.os.SystemClock;

import com.example.demo.net.NetService;
import com.example.demo.net.bean.LoginResponse;
import com.example.demo.net.bean.ProblemResponse;
import com.example.demo.presenter.contract.MainContract;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pen on 2017/7/27.
 */

public class MainPresenter extends XPresent<MainContract.View> implements MainContract.Presenter {
    @Override
    public void login(String phone, String password) {
        NetService.getApis2().login(phone, password, "fas")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse response) throws Exception {

                    }
                });
    }

    @Override
    public void getProblem(String search, int type) {
        NetService.getApis2().getProblem(type, search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        getV().showLoading();

                    }
                })
                .subscribe(new Consumer<ProblemResponse>() {
                    @Override
                    public void accept(ProblemResponse response) throws Exception {

                        getV().showContent();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
