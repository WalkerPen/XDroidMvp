package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.demo.bean.Station;
import com.example.demo.net.NetService;
import com.example.demo.net.bean.Check1Response;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class OtherRequestActivity extends AppCompatActivity {

    private ArrayList<Station> mStations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_request);
    }

    /**
     * 获取车站列表
     *
     * @param view
     */
    public void getStationList(View view) {
        NetService.getApis()
                .getStationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        String[] stations = json.split("\\d{0,}@");
                        for (int i = 0; i < stations.length; i++) {
                            if (i == 0) {
                                continue;
                            }
                            Station sta = new Station();
                            String station = stations[i];
                            String[] sp = station.split("\\|");
                            sta.shortPY = sp[0];
                            sta.name = sp[1];
                            sta.code = sp[2];
                            sta.longPY = sp[3];
                            mStations.add(sta);
                        }
                        String newJson = new Gson().toJson(mStations);
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void getWordCaptcha(View view) {
        NetService.getApis()
                .getWordCaptcha()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void check1(View view) {
        NetService.getApis()
                .check1("otn")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Check1Response>() {
                    @Override
                    public void accept(Check1Response responseBody) throws Exception {
                        NetService.tk = responseBody.newapptk;
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void check2(View view) {
        NetService.getApis()
                .check2(NetService.tk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void getContactList(View view) {
        NetService.getApis()
                .queryContact(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void deleteContact(View view) {
        NetService.getApis()
                .deleteContact("戴云华", "1", "430682196408195329", "N")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void getNoCompleteOrder(View view) {
        NetService.getApis()
                .getNoCompleteOrder("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }

    public void continuePay(View view) {
        NetService.getApis()
                .continuePay("E945226273", "pay", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        "".toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.toString();
                    }
                });
    }
}
