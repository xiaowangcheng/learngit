package com.manage.rain.net;

import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okrx.RxAdapter;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 * Created by wyf on 2018/1/3.
 */

public class Netapi extends  HttpNetUtils {

    /**
     * 加我需要验证
     * @param
     * @return
     */
    public static Observable<String> requestJson(String url ,JSONObject jsonObject){
        return   postOkGoRequest(url) .upJson(jsonObject)
                .getCall(StringConvert.create(), RxAdapter.<String>create());
    }

    public static Observable<String> requestParams(String url, HttpParams params){
        return getOkGoRequest(url).params(params)
                .getCall(StringConvert.create(), RxAdapter.<String>create());
    }
    public static Observable<String> requestFile(String url , List<File> files, HttpParams params){
        return   postOkGoRequest(url).addFileParams("photo",files).params(params)
                .getCall(StringConvert.create(), RxAdapter.<String>create());

    }
}
