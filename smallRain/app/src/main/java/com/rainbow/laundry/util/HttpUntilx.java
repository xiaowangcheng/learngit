package com.rainbow.laundry.util;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okrx.RxAdapter;
import com.rainbow.laundry.config.Urlx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by wyf on 2018/1/2.
 */

public class HttpUntilx {
    /**
     * get请求
     * @return
     */
    protected static GetRequest getOkGoRequest(String url){

        return   OkGo.getInstance().get(url);
    }

    protected static PostRequest posOkGoRequest(String url){
        return   OkGo.getInstance().post(url);
    }

    public static Observable<String> RequestInfo(String url, HttpParams httpParams){

            return   getOkGoRequest(url).params(httpParams)
                .getCall(StringConvert.create(), RxAdapter.<String>create()).doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread()) ;
    }

    public static Observable<String> RequestInfoPost(String url,HttpParams httpParams){

        return   posOkGoRequest(url).params(httpParams)
                .getCall(StringConvert.create(), RxAdapter.<String>create()).doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread()) ;
    }

    /**
     * 获取密保问题（找回密码时候）
     * @param
     * @return
     */
    public static Observable<String> getPayAnswer( HttpParams httpParams){
        return   getOkGoRequest(Urlx.LOGIN).params(httpParams)
                .getCall(StringConvert.create(), RxAdapter.<String>create()).doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
