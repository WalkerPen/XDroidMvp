package com.example.demo.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.demo.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.kit.MyUtils;
import cn.droidlover.xdroidmvp.mvp.LazyFragment;
import cn.droidlover.xdroidmvp.mvp.VDelegate;
import cn.droidlover.xdroidmvp.mvp.VDelegateBase;
import cn.droidlover.xdroidmvp.widget.StateController;

/**
 * Created by Pen on 2017/12/19.
 */

public abstract class LoadStateLazyFragment<P extends BasePersenter> extends LazyFragment implements BaseView<P>{
    private VDelegate vDelegate;
    private P p;
    private View rootView;

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

    @Nullable
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = layoutInflater.inflate(R.layout.fragment_base, null);
            initView();
            if (getLayoutId() > 0) {
                View content = layoutInflater.inflate(getLayoutId(), null);
                bindUI(content);
                mStateController.setContentView(content);
                showContent();
            }
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        setContentView(rootView);
        bindEvent();
        initData(savedInstanceState);
    }

    private void initView() {
        mStateController = (StateController) rootView.findViewById(R.id.state);
        mXLlHeader = (LinearLayout) rootView.findViewById(R.id.x_ll_header);
        mXFlToobar = (FrameLayout) rootView.findViewById(R.id.x_fl_toobar);

//        setToolbarBackground();
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

        if (needPadding) {
            mXFlToobar.setPadding(0, MyUtils.getStatusBarHeight(context), 0, 0);
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

    /*protected View getRootView() {
        return rootView;
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
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

    protected boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    protected int getOptionsMenuId() {
        return 0;
    }

    protected void bindEvent() {

    }

    protected void setContent(View contentView) {
        mStateController.setContentView(contentView);
        showContent();
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
