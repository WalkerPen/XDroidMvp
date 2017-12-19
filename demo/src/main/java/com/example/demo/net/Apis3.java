package com.example.demo.net;

import com.example.demo.bean.User;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Pen on 2017/9/22.
 */

public interface Apis3 {

    @GET("test/user.xml")
    Observable<ResponseBody> getXml();
}
