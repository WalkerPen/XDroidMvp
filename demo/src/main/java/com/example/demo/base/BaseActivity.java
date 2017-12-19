package com.example.demo.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.demo.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.kit.MyUtils;
import cn.droidlover.xdroidmvp.mvp.VDelegate;
import cn.droidlover.xdroidmvp.mvp.VDelegateBase;
import cn.droidlover.xdroidmvp.widget.StateController;

/**
 * Created by Pen on 2017/7/28.
 */

public abstract class BaseActivity<P extends BasePersenter> extends RxAppCompatActivity implements BaseView<P> {

    private VDelegate vDelegate;
    private P p;
    protected Activity context;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;
    private StateController mStateController;
    /**
     * 头部容器
     */
    private LinearLayout mXLlHeader;
    /**
     * toolbar容器
     */
    private FrameLayout mXFlToobar;
    /**
     * 默认toolbar
     */
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context = this;
        initView();
        if (getLayoutId() > 0) {
            View contentView = View.inflate(this, getLayoutId(), null);
            mStateController.setContentView(contentView);
            showContent();
            bindUI(contentView);
            bindEvent();
        }
        initData(savedInstanceState);

        //根据toolbar的高度设置toolbar容器的高度
        mXFlToobar.post(new Runnable() {
            @Override
            public void run() {
                mXFlToobar.getLayoutParams().height = mXFlToobar.getChildAt(0).getHeight();
                mXFlToobar.requestLayout();
            }
        });
    }

    private void initView() {
        mStateController = (StateController) findViewById(R.id.state);
        mXLlHeader = (LinearLayout) findViewById(R.id.x_ll_header);
        mXFlToobar = (FrameLayout) findViewById(R.id.x_fl_toobar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (immersion() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏，用界面最上面代替状态栏背景
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setToolbarBackground();


    }

    /**
     * 将头部中最上面的控件的背景转移到头部中
     */
    private void setToolbarBackground() {
        if (mXFlToobar.getChildCount() != 1) {
            throw new RuntimeException("toolbar容器内必须且只能存在1个子控件");
        }
        Drawable drawable = mXFlToobar.getChildAt(0).getBackground();
        if (drawable != null) {
            mXFlToobar.getChildAt(0).setBackground(null);
            mXFlToobar.setBackground(drawable);
        }
    }

    /**
     * 设置toolbar，这个view将会从原来的布局中脱离出来，而成为新的toolbar
     * @param toolbar
     * @param needPadding
     */
    protected void setToolbar(View toolbar, boolean needPadding) {
        mXFlToobar.removeAllViews();
        if (toolbar != null) {
            ((ViewGroup) toolbar.getParent()).removeView(toolbar);
            mXFlToobar.addView(toolbar);
            setToolbarBackground();
        }

        //如果开启沉浸式，并且需要padding，并且版本大于4.4
        if (immersion() && needPadding && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mXFlToobar.setPadding(0, MyUtils.getStatusBarHeight(this), 0, 0);
        } else {
            mXFlToobar.setPadding(0, 0, 0, 0);
        }
    }

    /**
     * 将控件加入头部，可在content内容显示或隐藏时保持不变
     *
     * @param header
     */
    protected void addHeader(View header) {
        ((ViewGroup) header.getParent()).removeView(header);
        mXLlHeader.addView(header);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    protected int getOptionsMenuId() {
        return 0;
    }

    protected void bindEvent() {

    }

    /**
     * 是否开启沉浸式 默认true
     * @return
     */
    protected boolean immersion() {
        return true;
    }


    @Override
    public void showLoading() {
        mStateController.showLoading();
    }

    @Override
    public void showContent() {
        mStateController.showContent();
    }

    @Override
    public void showError() {
        mStateController.showError();
    }

    @Override
    public void showEmpty() {
        mStateController.showEmpty();
    }

    protected abstract P newP();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract int getLayoutId();
}
