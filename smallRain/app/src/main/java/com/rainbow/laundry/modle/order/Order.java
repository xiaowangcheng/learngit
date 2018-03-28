package com.rainbow.laundry.modle.order;


import com.rainbow.laundry.modle.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyc on 2018/1/2.
 */

public class Order {


    /**
     * status : 1
     * msg : null
     * data : {"indentList":[{"id":"02f33e9f99a0470f811363c642fae6e7","status":-1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511516810000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"2f813aee5ce7456783fe3de32fbeb386","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517079000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"529b2116e19f428cb12f2028253db3b2","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":44,"used":0,"address":null,"createtime":1511507992000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"3,4,5,2"},{"id":"6397eedfe9004ac480ee95c5e1452ca4","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517187000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"6540e5509c56468baa0c73c5d929de62","status":5,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":100,"used":0,"address":null,"createtime":1508904028000,"paystatus":0,"user":null,"employeeId":"efcc0666b01f4c919effe2cf0e83ff4c","employee":null,"serviceids":null},{"id":"66288eae6c6d428181c1fa6c9c962221","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517143000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"91961534499846f3b70c7448bda6951c","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517055000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"b4a66cfea40a4a509f9f2c1c2edb9aab","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":1000,"used":0,"address":null,"createtime":1511486584000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2,3"},{"id":"f63ae544f2d04b6382943cdb63eadda4","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511516974000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"}],"pageInfo":{"total":9,"currentPage":1,"totalPage":1}}
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

    public static class DataEntity implements Serializable{
        /**
         * indentList : [{"id":"02f33e9f99a0470f811363c642fae6e7","status":-1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511516810000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"2f813aee5ce7456783fe3de32fbeb386","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517079000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"529b2116e19f428cb12f2028253db3b2","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":44,"used":0,"address":null,"createtime":1511507992000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"3,4,5,2"},{"id":"6397eedfe9004ac480ee95c5e1452ca4","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517187000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"6540e5509c56468baa0c73c5d929de62","status":5,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":100,"used":0,"address":null,"createtime":1508904028000,"paystatus":0,"user":null,"employeeId":"efcc0666b01f4c919effe2cf0e83ff4c","employee":null,"serviceids":null},{"id":"66288eae6c6d428181c1fa6c9c962221","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517143000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"91961534499846f3b70c7448bda6951c","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511517055000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"},{"id":"b4a66cfea40a4a509f9f2c1c2edb9aab","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":1000,"used":0,"address":null,"createtime":1511486584000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2,3"},{"id":"f63ae544f2d04b6382943cdb63eadda4","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userId":"dddsadasd","total":11,"used":0,"address":null,"createtime":1511516974000,"paystatus":0,"user":null,"employeeId":null,"employee":null,"serviceids":"1,2"}]
         * pageInfo : {"total":9,"currentPage":1,"totalPage":1}
         */

        private PageInfo pageInfo;
        private List<IndentListEntity> indentList;

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }

        public void setIndentList(List<IndentListEntity> indentList) {
            this.indentList = indentList;
        }


        public List<IndentListEntity> getIndentList() {
            return indentList;
        }



        public static class IndentListEntity implements Serializable{
            /**
             * id : 02f33e9f99a0470f811363c642fae6e7
             * status : -1
             * combos : null
             * addressId : b5faeb7ba1624630a215c38fe8d62561
             * userId : dddsadasd
             * total : 11.0
             * used : 0
             * address : null
             * createtime : 1511516810000
             * paystatus : 0
             * user : null
             * employeeId : null
             * employee : null
             * serviceids : 1,2
             */

            private String addTime;
            private String outTime;
            private String id;
            private int status;
            private int userEstateId;
            private int estateId;
            private String estateName;
            private String detailed;
            private int userSex;
            private String userSexStr;
            private String phoneNumber;
            private  String comName;
            private String addressId;
            private String userId;
            private double total;
            private int used;
            private int payStatus;

            private String serviceIds;
            private String remark;
            private int cancelId;
            private  double realMoney;
            private double payMoney;
            private int kuponListId;
            private int abnormal;
            private  String orderNumber;
            private int hangersId;
            private int startHanger;
            private  int endHanger;
            private String payWay;
            private  String barCode;
            private  String indentDress;


            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getOutTime() {
                return outTime;
            }

            public void setOutTime(String outTime) {
                this.outTime = outTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUserEstateId() {
                return userEstateId;
            }

            public void setUserEstateId(int userEstateId) {
                this.userEstateId = userEstateId;
            }

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

            public String getDetailed() {
                return detailed;
            }

            public void setDetailed(String detailed) {
                this.detailed = detailed;
            }

            public int getUserSex() {
                return userSex;
            }

            public void setUserSex(int userSex) {
                this.userSex = userSex;
            }

            public String getUserSexStr() {
                return userSexStr;
            }

            public void setUserSexStr(String userSexStr) {
                this.userSexStr = userSexStr;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getComName() {
                return comName;
            }

            public void setComName(String comName) {
                this.comName = comName;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getTotal() {
                return total;
            }

            public void setTotal(double total) {
                this.total = total;
            }

            public int getUsed() {
                return used;
            }

            public void setUsed(int used) {
                this.used = used;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public String getServiceids() {
                return serviceIds;
            }

            public void setServiceids(String serviceids) {
                this.serviceIds = serviceids;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getCancelId() {
                return cancelId;
            }

            public void setCancelId(int cancelId) {
                this.cancelId = cancelId;
            }

            public double getRealMoney() {
                return realMoney;
            }

            public void setRealMoney(double realMoney) {
                this.realMoney = realMoney;
            }

            public double getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(double payMoney) {
                this.payMoney = payMoney;
            }

            public int getKuponListId() {
                return kuponListId;
            }

            public void setKuponListId(int kuponListId) {
                this.kuponListId = kuponListId;
            }

            public int getAbnormal() {
                return abnormal;
            }

            public void setAbnormal(int abnormal) {
                this.abnormal = abnormal;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public int getHangersId() {
                return hangersId;
            }

            public void setHangersId(int hangersId) {
                this.hangersId = hangersId;
            }

            public int getStartHanger() {
                return startHanger;
            }

            public void setStartHanger(int startHanger) {
                this.startHanger = startHanger;
            }

            public int getEndHanger() {
                return endHanger;
            }

            public void setEndHanger(int endHanger) {
                this.endHanger = endHanger;
            }

            public String getPayWay() {
                return payWay;
            }

            public void setPayWay(String payWay) {
                this.payWay = payWay;
            }

            public String getBarCode() {
                return barCode;
            }

            public void setBarCode(String barCode) {
                this.barCode = barCode;
            }

            public String getIndentDress() {
                return indentDress;
            }

            public void setIndentDress(String indentDress) {
                this.indentDress = indentDress;
            }
        }
    }
}
