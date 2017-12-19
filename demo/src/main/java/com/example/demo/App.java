package com.example.demo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Pen on 2017/8/10.
 */

public class App extends Application {

    public static Context context;
    private static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        init();
    }

    private void init() {
        XApi.init(this);

        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        mPatchManager.loadPatch();

    }

    public static void load() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch.apatch";
        File file = new File(path);
        boolean exists = file.exists();
        try {
            mPatchManager.addPatch(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
