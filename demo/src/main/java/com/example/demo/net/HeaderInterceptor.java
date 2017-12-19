package com.example.demo.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pen on 2017/7/27.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("name", "pen")
                .addHeader("age", "25")
                .addHeader("gender", "man")
                .build();

        return chain.proceed(request);
    }
}
