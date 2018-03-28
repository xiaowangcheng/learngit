package com.rainbow.laundry.modle.good;


import com.rainbow.laundry.modle.Base;

/**
 * Created by wyc on 2018/1/3.
 */

public class Pro_good extends Base {
    /**
     * id : e913b87710ab4f9c90bf5de432268b33
     * sort : 1
     * classes : 11
     * price : 11.0
     * comboname : 鞋子
     * imgurl : /upload/e913b87710ab4f9c90bf5de432268b33headimg.jpg
     * specify : null
     * remark : null
     * secondary : null
     * introduce : 11发多少
     * status : 1
     * count : 0
     */

    private String id;
    private int sort;
    private int classes;
    private double price;
    private String comboname;
    private String imgurl;
    private String specify;
    private String remark;
    private String secondary;
    private String introduce;
    private int status;
    private int count;
    private int order_count=0; //选择数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComboname() {
        return comboname;
    }

    public void setComboname(String comboname) {
        this.comboname = comboname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getSpecify() {
        return specify;
    }

    public void setSpecify(String specify) {
        this.specify = specify;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pro_good){
            Pro_good cb= (Pro_good) obj;
            return  this.id.equals(cb.getId());
        }
        return super.equals(obj);
    }
}
