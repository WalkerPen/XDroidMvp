package com.example.demo.net;

import com.example.demo.bean.LoginResponse;
import com.example.demo.net.bean.Check1Response;
import com.example.demo.net.bean.CityListResponse;

import java.util.Map;
import java.util.Random;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Pen on 2017/7/27.
 */

public interface Apis {

    @GET("common/channel/channel_list")
    Observable<CityListResponse> getCityList();

    /**
     * 获取验证图片
     *
     * @param loginSite
     * @param module
     * @param rand
     * @return
     */
    /*@GET("passport/captcha/captcha-image")
    Observable<ResponseBody> getCaptchaImage(@Query("login_site") String loginSite, @Query("module") String module,
                                             @Query("rand") String rand);*/

    @GET("passport/captcha/captcha-image")
    Observable<ResponseBody> getCaptchaImage(@QueryMap Map<String, String> Params);

    /**
     * 验证图片
     *
     * @param answer
     * @param loginSite
     * @param rand
     * @return
     */
    @POST("passport/captcha/captcha-check")
    @FormUrlEncoded
    Observable<ResponseBody> checkCaptcha(@Field("answer") String answer, @Field("login_site") String loginSite, @Field("rand") String rand);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param appid
     * @return
     */
    @POST("passport/web/login")
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("username") String username, @Field("password") String password, @Field("appid") String appid);

    @GET("otn/resources/js/framework/station_name.js?station_version=1.9023")
    Observable<ResponseBody> getStationList();

    /**
     * 获取文字验证码
     *
     * @return
     */
    @GET("otn/passcodeNew/getPassCodeNew?module=other&rand=sjrand")
    Observable<ResponseBody> getWordCaptcha();

    /**
     * 获取联系人列表
     *
     * @return
     */
    @POST("otn/confirmPassenger/getPassengerDTOs")
    @FormUrlEncoded
    Observable<ResponseBody> getContactList(@Field("_json_att") String json_att, @Field("REPEAT_SUBMIT_TOKEN") String token);

    @GET("otn/confirmPassenger/initDc?_json_att=")
    Observable<ResponseBody> getToken();

    @POST("otn/passengers/query")
    @FormUrlEncoded
    Observable<ResponseBody> queryContact(@Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    @POST("passport/web/auth/uamtk")
    @FormUrlEncoded
    Observable<Check1Response> check1(@Field("appid") String appid);

    @POST("otn/uamauthclient")
    @FormUrlEncoded
    Observable<ResponseBody> check2(@Field("tk") String tk);

    /**
     * 删除联系人
     *
     * @param name
     * @param code
     * @param no
     * @param isUserSelf
     * @return
     */
    @POST("otn/passengers/delete")
    @FormUrlEncoded
    Observable<ResponseBody> deleteContact(@Field("passenger_name") String name, @Field("passenger_id_type_code") String code,
                                           @Field("passenger_id_no") String no, @Field("isUserSelf") String isUserSelf);

    /**
     * 获取未完成订单
     *
     * @param _json_att
     * @return
     */
    @POST("otn/queryOrder/queryMyOrderNoComplete")
    @FormUrlEncoded
    Observable<ResponseBody> getNoCompleteOrder(@Field("_json_att") String _json_att);

    /**
     * 继续支付
     * @return
     */
    @POST("otn/queryOrder/continuePayNoCompleteMyOrder")
    @FormUrlEncoded
    Observable<ResponseBody> continuePay(@Field("sequence_no") String sequence_no, @Field("pay_flag") String pay_flag, @Field("_json_att") String _json_att);
}
