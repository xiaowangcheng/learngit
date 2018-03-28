package com.rainbow.laundry.modle.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyc on 2018/1/3.
 */

public class Pro_GoodsList implements Serializable{


    /**
     * status : 1
     * msg : null
     * data : [{"comboid":"e913b87710ab4f9c90bf5de432268b33","sort":1,"classes":11,"price":11,"comboname":"鞋子","imgurl":"/upload/e913b87710ab4f9c90bf5de432268b33headimg.jpg","specify":null,"remark":null,"secondary":null,"introduce":"11发多少","status":1,"count":0},{"comboid":"c0525d2724c04cc293d1a5e75c8d183e","sort":1,"classes":11,"price":11,"comboname":"洗上衣","imgurl":"/upload/c0525d2724c04cc293d1a5e75c8d183eheadimg.jpg","specify":null,"remark":null,"secondary":null,"introduce":"阿道夫","status":1,"count":0},{"comboid":"f8e99a2f394e45b1877953b5d16f4e35","sort":1,"classes":11,"price":11,"comboname":"新加","imgurl":"/upload/f8e99a2f394e45b1877953b5d16f4e35headimg.png","specify":"11","remark":"22","secondary":"22","introduce":"大师傅","status":0,"count":0}]
     */

    private int status;
    private Object msg;
    private List<Pro_good> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public void setData(List<Pro_good> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getMsg() {
        return msg;
    }

    public List<Pro_good> getData() {
        return data;
    }


}
