package com.example.demo.net;

import com.example.demo.net.bean.CityListResponse;
import com.example.demo.net.bean.LoginResponse;
import com.example.demo.net.bean.ProblemResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Pen on 2017/7/27.
 */

public interface Apis2 {

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponse> login(@Field("phone") String phone, @Field("password") String password, @Field("jpush_id") String jpush_id);

    @FormUrlEncoded
    @POST("ops_order/problem_search")
    Observable<ProblemResponse> getProblem(@Field("type") int type, @Field("search") String search);
}
