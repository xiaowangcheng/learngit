package com.rainbow.laundry.modle.address;


import com.rainbow.laundry.modle.Base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyc on 2018/1/2.
 */

public class Address extends Base {


    /**
     * status : 1
     * msg : null
     * data : [{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"1111","name":"啊","userId":2,"status":0,"usersex":0,"isetw":1},{"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15252525252","name":"个","userId":1,"status":0,"usersex":0,"isetw":1},{"id":4,"province":"北京市","city":"北京市","county":"东城区","detailed":"鄞州区觉得似的","phoneNumber":"18767160842","name":"王成","userId":5,"status":0,"usersex":0,"isetw":1}]
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

    public static class DataEntity  implements Serializable{

        /**
         * id : 4
         * province : 北京市
         * city : 北京市
         * county : 东城区
         * detailed : 鄞州区觉得似的
         * phoneNumber : 18767160842
         * name : 王成
         * userId : 5
         * status : 0
         * usersex : 0
         * isetw : 1
         * estateName :
         * estateId
         */

        private int id;
        private String province;
        private String city;
        private String county;
        private String detailed;
        private String phoneNumber;
        private String conName;
        private int userId;
        private int status;
        private int usersex;
        private int isetw;
        private String estateName;
        private int estateId;

        public int getEstateId() {
            return estateId;
        }

        public void setEstateId(int estateId) {
            this.estateId = estateId;
        }

        public String getEstateName() {
            return estateName;
        }

        public void setEstateName(String estateName) {
            this.estateName = estateName;
        }

        public void setId(int id) {
            this.id = id;
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

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setName(String name) {
            this.conName = name;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setUsersex(int usersex) {
            this.usersex = usersex;
        }

        public void setIsetw(int isetw) {
            this.isetw = isetw;
        }

        public int getId() {
            return id;
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getName() {
            return conName;
        }

        public int getUserId() {
            return userId;
        }

        public int getStatus() {
            return status;
        }

        public int getUsersex() {
            return usersex;
        }

        public int getIsetw() {
            return isetw;
        }
    }
}
