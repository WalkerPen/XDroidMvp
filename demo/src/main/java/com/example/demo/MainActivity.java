package com.example.demo;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.demo.base.BaseActivity;
import com.example.demo.bean.LoginResponse;
import com.example.demo.net.NetService;
import com.example.demo.presenter.MainPresenter;
import com.example.demo.presenter.contract.MainContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    private MyImageView mImage;

    public void request(View view) {
        startActivity(new Intent(this, XmlActivity.class));
    }

    public void getProblem(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("login_site", "E");
        params.put("module", "login");
        params.put("rand", "sjrand");
        params.put(new Random().nextDouble() + "", "");

        NetService.getApis()
                .getCaptchaImage(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        byte[] bytes = responseBody.bytes();
                        Glide.with(MainActivity.this).load(bytes).into(mImage);
                        mImage.clearPoint();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void check(View view) {
        String point = mImage.getPointString();
        NetService.getApis().checkCaptcha(point, "E", "sjrand")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        json.toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void login(View view) {
        NetService.getApis().login("ymljuly@163.com", "yml123", "otn")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse responseBody) throws Exception {
                        NetService.token = responseBody.uamtk;
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void andfix(View view) {
        toast();
    }

    public void load(View view) {
        new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            App.load();
                        }
                    }
                });
    }

    public void toast() {
        Toast.makeText(this, "bug已修复", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mImage = (MyImageView) findViewById(R.id.image);

        ILFactory.getLoader().init(this);

//        String url = "https://dimg02.c-ctrip.com/images/fd/tg/g1/M05/2A/B7/CghzflVQaLmAWMv0AAEyAoZKHLY067_C_422_236.jpg";
        ImageView ivHeader = (ImageView) findViewById(R.id.iv_header);
        ILFactory.getLoader().loadResource(ivHeader, R.drawable.biting, null);
//        addHeader(ivHeader);
        setToolbar(ivHeader, false);
//        addHeader(findViewById(R.id.bt_request));
        getSupportActionBar().setTitle("主页");

        final String[] per = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};

        if (should(per)) {
            Log.d("测试", "拒绝过一次了");
        } else {
            Log.d("测试", "未请求，或者同意，或者永久拒绝");
        }
        getRxPermissions()
                .request(per)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Log.d("测试", "请求成功");
                        } else {
                            if(should(per)) {
                                Log.d("测试", "单次拒绝");
                            } else {
                                Log.d("测试", "永久拒绝");
                                Intent intent = new Intent(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.Presenter newP() {
        return new MainPresenter();
    }

    @Override
    protected int getOptionsMenuId() {
        return R.menu.menu_main;
    }

    @Override
    protected boolean immersion() {
        return false;
    }

    private boolean should(String[] permissions) {
        for(int i = 0; i < permissions.length; i ++) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                return true;
            }
        }
        return false;
    }
}
