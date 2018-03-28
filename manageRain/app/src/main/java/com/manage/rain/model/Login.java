package com.manage.rain.model;

/**
 * Created by wyf on 2018/1/6.
 */

public class Login {


    /**
     * status : 1
     * msg : null
     * data : {"employeeid":"dde358ab6b8b4da08fa904ae299b6073","employeelevel":1,"employeename":"阿丰","token":"eyJhbGciOiJIUzI1NiJ9.eyJzdGFmZmlkIjoiZGRlMzU4YWI2YjhiNGRhMDhmYTkwNGFlMjk5YjYwNzMifQ.GNYjkmaUGpZp9Hh7gsbAl2VaV9eBYQQ6a-cTQ0_HVnI"}
     */

    private int status;
    private Object msg;
    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * employeeid : dde358ab6b8b4da08fa904ae299b6073
         * employeelevel : 1
         * employeename : 阿丰
         * token : eyJhbGciOiJIUzI1NiJ9.eyJzdGFmZmlkIjoiZGRlMzU4YWI2YjhiNGRhMDhmYTkwNGFlMjk5YjYwNzMifQ.GNYjkmaUGpZp9Hh7gsbAl2VaV9eBYQQ6a-cTQ0_HVnI
         */

        private String employeeid;
        private int employeelevel;
        private String employeename;
        private String token;

        public void setEmployeeid(String employeeid) {
            this.employeeid = employeeid;
        }

        public void setEmployeelevel(int employeelevel) {
            this.employeelevel = employeelevel;
        }

        public void setEmployeename(String employeename) {
            this.employeename = employeename;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEmployeeid() {
            return employeeid;
        }

        public int getEmployeelevel() {
            return employeelevel;
        }

        public String getEmployeename() {
            return employeename;
        }

        public String getToken() {
            return token;
        }
    }
}
