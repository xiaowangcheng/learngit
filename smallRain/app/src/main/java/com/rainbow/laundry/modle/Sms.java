package com.rainbow.laundry.modle;

import java.io.Serializable;

/**
 * Created by wyc on 2018/1/2.
 */

public class Sms  implements Serializable {


    /**
     * status : 0
     * msg : 验证码发送成功
     * data :
     */

    private int status;
    private String msg;
    private String data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }
}
