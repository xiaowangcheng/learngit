package com.rainbow.laundry.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by wyf on 2018/1/2.
 */

public class JSON2Class {
    private static Gson mGson = new Gson();

    private static void init() {
        if (mGson == null) {
            synchronized (JSON2Class.class) {
                if (mGson == null) {
                    mGson = new Gson();
                }
            }
        }
    }
//    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {

    public static <T> T toClass(String string, Class<T> t) {
        return mGson.fromJson(string, t);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        if (json == null) {
            return null;
        } else {
            return mGson.fromJson(json, typeOfT);
        }
    }

    //    public static <T> List<T> toList(String string ,final Class<T> tClass)
//    {
//        Type objectType = new TypeToken<List<T>>() {}.getAdapterItemType();
//        return mGson.fromJson(string,objectType);
//        List<Student> retList = gson.fromJson(s2,
//                new TypeToken<List<Student>>() {
//                }.getAdapterItemType());
//    }
    public static Gson getGson() {
        return mGson;
    }
}
