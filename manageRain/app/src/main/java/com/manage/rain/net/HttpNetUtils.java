package com.manage.rain.net;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;

/**
 * Created by wyf on 2018/1/3.
 */

public class HttpNetUtils {
    /**
     * post请求添加请求头
     * @return
     */
    protected static PostRequest postOkGoRequest(String url){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.put(HttpHeaders.HEAD_KEY_COOKIE, "");

        return   OkGo.getInstance().post(url).
                headers(httpHeaders);
    }

    /**
     * put请求添加请求头
     * @return
     */
    protected static PutRequest putOkGoRequest(String url){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.put(HttpHeaders.HEAD_KEY_COOKIE, "");

        return   OkGo.getInstance().put(url).
                headers(httpHeaders);
    }

    /**
     * get请求
     * @return
     */
    protected static GetRequest getOkGoRequest(String url){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.put(HttpHeaders.HEAD_KEY_COOKIE, "");

        return   OkGo.getInstance().get(url).
                headers(httpHeaders);
    }
}
