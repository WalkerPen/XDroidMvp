package com.example.demo.net;

import android.text.TextUtils;

import com.example.demo.App;
import com.example.demo.net.NetService;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pen on 2017/8/11.
 */

public class CookieInterceptor {

    private static final String COOKIE = "cookie";

    public static class Save implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            if (!response.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : response.headers("Set-Cookie")) {
                    cookies.add(header);
                }
                //存到sp中
                SharedPref.getInstance(App.context).putStringSet(COOKIE, cookies);
            }

            return response;
        }
    }


    public static class Load implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            //从sp取出
            Set<String> cookies = SharedPref.getInstance(App.context).getStringSet(COOKIE, null);
            if(!TextUtils.isEmpty(NetService.token)) {
//                cookies.add("uamtk=" + NetService.token);
            }
            if(cookies != null) {
                for (String cookie : cookies) {
                    builder.addHeader("Cookie", cookie);
                }
            }

            return chain.proceed(builder.build());
        }
    }
}
