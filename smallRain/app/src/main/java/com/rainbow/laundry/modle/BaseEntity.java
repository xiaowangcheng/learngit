package com.rainbow.laundry.modle;

import java.util.List;

/**
 * Created by wyc on 2018/1/2.
 */

public class BaseEntity<T> {


    /**
     * status : 1
     * msg : null
     * data : [{"addressid":"7e724a9f9eda42c7b32b929da23d3913","province":"浙江","city":"宁波","county":"鄞州区","detailed":"南部商务区","phonenumber":"18720147511","name":"郝顺","userid":"dddsadasd","status":0},{"addressid":"b5faeb7ba1624630a215c38fe8d62561","province":"浙江","city":"宁波","county":"镇海区","detailed":"不用知道","phonenumber":"15888576057","name":"郝顺","userid":"dddsadasd","status":0}]
     */

    private int status;
    private Object msg;
    private List<T> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<T> getData( ) {
        return  data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
