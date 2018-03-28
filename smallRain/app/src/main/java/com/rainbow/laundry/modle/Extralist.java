package com.rainbow.laundry.modle;

import java.util.List;

/**
 * Created by wangcheng on 2018/3/1.
 */

public class Extralist {


    /**
     * status : 1
     * msg : null
     * data : {"balance":0,"extraList":[{"id":6,"recharge":200,"presenter":50,"addTime":null},{"id":7,"recharge":300,"presenter":150,"addTime":null},{"id":8,"recharge":1000,"presenter":20,"addTime":null}]}
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
         * balance : 0.0
         * extraList : [{"id":6,"recharge":200,"presenter":50,"addTime":null},{"id":7,"recharge":300,"presenter":150,"addTime":null},{"id":8,"recharge":1000,"presenter":20,"addTime":null}]
         */

        private double balance;
        private List<ExtraListEntity> extraList;

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void setExtraList(List<ExtraListEntity> extraList) {
            this.extraList = extraList;
        }

        public double getBalance() {
            return balance;
        }

        public List<ExtraListEntity> getExtraList() {
            return extraList;
        }

        public static class ExtraListEntity {
            /**
             * id : 6
             * recharge : 200.0
             * presenter : 50.0
             * addTime : null
             */

            private int id;
            private double recharge;
            private double presenter;
            private Object addTime;

            public void setId(int id) {
                this.id = id;
            }

            public void setRecharge(double recharge) {
                this.recharge = recharge;
            }

            public void setPresenter(double presenter) {
                this.presenter = presenter;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public int getId() {
                return id;
            }

            public double getRecharge() {
                return recharge;
            }

            public double getPresenter() {
                return presenter;
            }

            public Object getAddTime() {
                return addTime;
            }
        }
    }
}
