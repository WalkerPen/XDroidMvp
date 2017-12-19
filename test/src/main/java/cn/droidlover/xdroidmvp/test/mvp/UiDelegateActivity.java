package cn.droidlover.xdroidmvp.test.mvp;

import android.os.Bundle;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by wanglei on 2017/1/30.
 */

public class UiDelegateActivity extends XActivity {
    @Override
    public void initData(Bundle savedInstanceState) {
        getvDelegate().toastShort("");
        getvDelegate().gone(true, null);
        getvDelegate().toastLong("");
//        ...
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public IPresent newP() {
        return null;
    }
}
