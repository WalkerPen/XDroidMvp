package com.example.demo.net;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Pen on 2017/7/27.
 */

public class NetService {

    public static String BASE_URL = "https://kyfw.12306.cn";
    private static String BASE_URL2 = "http://menshen.datuhongan.com";
    private static String BASE_URL3 = "http://192.168.2.102:8080";
    private static Apis sApis;
    private static Apis2 sApis2;
    private static Apis3 sApis3;
    private static List<okhttp3.Cookie> sCookies = new ArrayList<>();
    public static String token;
    public static String tk;

    static {
        XApi.registerProvider(new Provider());
    }

    private NetService() {}

    public static Apis getApis() {
        if(sApis == null) {
            sApis = XApi.getInstance().getRetrofit(BASE_URL, true).create(Apis.class);
        }
        return sApis;
    }

    public static Apis2 getApis2() {
        if(sApis2 == null) {
            sApis2 = XApi.getInstance().getRetrofit(BASE_URL2, true).create(Apis2.class);
        }
        return sApis2;
    }

    public static Apis3 getApis3() {
        if(sApis3 == null) {
            sApis3 = XApi.getInstance().getRetrofit(BASE_URL3, true).create(Apis3.class);
        }
        return sApis3;
    }

    private static class Provider implements NetProvider{
        @Override
        public Interceptor[] configInterceptors() {
            return new Interceptor[] {new HeaderInterceptor()};
        }

        @Override
        public void configHttps(OkHttpClient.Builder builder) {

        }

        @Override
        public CookieJar configCookie() {
            return new MyCookie();
        }

        @Override
        public RequestHandler configHandler() {
            return null;
        }

        @Override
        public long configConnectTimeoutMills() {
            return 10 * 1000;
        }

        @Override
        public long configReadTimeoutMills() {
            return 10 * 1000;
        }

        @Override
        public boolean configLogEnable() {
            return true;
        }

        @Override
        public boolean handleError(NetError error) {
            return true;
        }
    }

    private static class MyCookie implements CookieJar {
        @Override
        public void saveFromResponse(HttpUrl url, List<okhttp3.Cookie> cookies) {
            sCookies.clear();
            sCookies.addAll(cookies);
        }

        @Override
        public List<okhttp3.Cookie> loadForRequest(HttpUrl url) {
            return sCookies;
        }
    }
}
