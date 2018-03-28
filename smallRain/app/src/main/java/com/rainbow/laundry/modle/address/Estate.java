package com.rainbow.laundry.modle.address;

import java.util.List;

/**
 * Created by wangcheng on 2018/2/25.
 */

public class Estate {

    /**
     * status : 1
     * msg : null
     * data : [{"id":3,"estateName":"华山花园","estateAddTime":1519531144000,"estatePartitionId":2,"area":null,"estateDistrictId":null},{"id":4,"estateName":"紫金花园","estateAddTime":null,"estatePartitionId":4,"area":null,"estateDistrictId":null}]
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

    public static class DataEntity {
        /**
         * id : 3
         * estateName : 华山花园
         * estateAddTime : 1519531144000
         * estatePartitionId : 2
         * area : null
         * estateDistrictId : null
         */

        private int id;
        private String estateName;
        private long estateAddTime;
        private int estatePartitionId;
        private Object area;
        private Object estateDistrictId;

        public void setId(int id) {
            this.id = id;
        }

        public void setEstateName(String estateName) {
            this.estateName = estateName;
        }

        public void setEstateAddTime(long estateAddTime) {
            this.estateAddTime = estateAddTime;
        }

        public void setEstatePartitionId(int estatePartitionId) {
            this.estatePartitionId = estatePartitionId;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public void setEstateDistrictId(Object estateDistrictId) {
            this.estateDistrictId = estateDistrictId;
        }

        public int getId() {
            return id;
        }

        public String getEstateName() {
            return estateName;
        }

        public long getEstateAddTime() {
            return estateAddTime;
        }

        public int getEstatePartitionId() {
            return estatePartitionId;
        }

        public Object getArea() {
            return area;
        }

        public Object getEstateDistrictId() {
            return estateDistrictId;
        }
    }
}
