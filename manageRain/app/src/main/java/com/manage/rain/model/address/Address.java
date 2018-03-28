package com.manage.rain.model.address;

import com.manage.rain.model.Base;

import java.io.Serializable;
import java.util.List;


public class Address extends Base {


    /**
     * status : 1
     * msg : null
     * data : [{"addressid":"7e724a9f9eda42c7b32b929da23d3913","province":"浙江","city":"宁波","county":"鄞州区","detailed":"南部商务区","phonenumber":"18720147511","name":"郝顺","userid":"dddsadasd","status":0},{"addressid":"b5faeb7ba1624630a215c38fe8d62561","province":"浙江","city":"宁波","county":"镇海区","detailed":"不用知道","phonenumber":"15888576057","name":"郝顺","userid":"dddsadasd","status":0}]
     */

    private int status;
    private Object msg;
    private List<DataEntity> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable {
        /**
         * addressid : 7e724a9f9eda42c7b32b929da23d3913
         * province : 浙江
         * city : 宁波
         * county : 鄞州区
         * detailed : 南部商务区
         * phonenumber : 18720147511
         * name : 郝顺
         * userid : dddsadasd
         * status : 0
         */

        private String addressid;
        private String province;
        private String city;
        private String county;
        private String detailed;
        private String phonenumber;
        private String name;
        private String userid;
        private int status;
        private String usersex;
        private int isetw;

        public int getIsetw() {
            return isetw;
        }

        public void setIsetw(int isetw) {
            this.isetw = isetw;
        }

        public String getUsersex() {
            return usersex;
        }

        public void setUsersex(String usersex) {
            this.usersex = usersex;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public void setDetailed(String detailed) {
            this.detailed = detailed;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddressid() {
            return addressid;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getCounty() {
            return county;
        }

        public String getDetailed() {
            return detailed;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public String getName() {
            return name;
        }

        public String getUserid() {
            return userid;
        }

        public int getStatus() {
            return status;
        }
    }
}
