package com.manage.rain.model;

/**
 * Created by wangcheng on 2018/2/10.
 */

public class UpdateState {


    /**
     * data : 添加异常成功
     * message : suss
     * success : true
     */

    private String data;
    private String message;
    private boolean success;

    public void setData(String data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }
}
