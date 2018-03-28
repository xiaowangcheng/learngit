package com.manage.rain.model;

import java.util.List;

/**
 * Created by wyf on 2018/1/6.
 */

public class Order extends Base{


    /**
     * status : 1
     * msg : null
     * data : {"indentList":[{"addTime":"2018-02-06 23:32","outTime":"2018-02-08 23:32","id":18,"address":{"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15958273487","name":"个","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":3,"userId":1,"total":11,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"3,4","remark":"阿斯蒂芬","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"d6d2a692cb3d43aea12ccd89c932a8c8","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-02-04 18:45","outTime":"2018-02-06 18:45","id":14,"address":{"id":11,"province":"北京市","city":"北京市","county":"东城区","detailed":"阿斯蒂芬","phoneNumber":"15958273488","name":"阿斯蒂芬","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":11,"userId":3,"total":11,"used":0,"payStatus":0,"user":{"id":3,"phoneNumber":"15958273488","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"5","remark":"阿试","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"a17b5b28629d47e59fe2edabc86a9b28","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-02-03 17:03","outTime":"2018-02-05 17:03","id":13,"address":{"id":11,"province":"北京市","city":"北京市","county":"东城区","detailed":"阿斯蒂芬","phoneNumber":"15958273488","name":"阿斯蒂芬","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":11,"userId":3,"total":22,"used":0,"payStatus":0,"user":{"id":3,"phoneNumber":"15958273488","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2","remark":"发顺丰","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"973a828b856a4e2ba214e8780d4623c0","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-02-03 16:21","outTime":"2018-02-05 16:21","id":12,"address":{"id":0,"province":null,"city":null,"county":null,"detailed":null,"phoneNumber":"15958273488","name":null,"userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":4,"userId":3,"total":33,"used":0,"payStatus":0,"user":{"id":3,"phoneNumber":"15958273488","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"1,2","remark":"asdf","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"bdda559ce64b4bca9154d023e98a5b79","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 02:14","outTime":"2018-01-26 02:14","id":8,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":11,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2","remark":"1","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"d91dabb4c4684f86bacc9d06c442e3b9","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 02:13","outTime":"2018-01-26 02:13","id":7,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":11,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2","remark":"1","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"180fd661aa604c8196c408ec78c43f02","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 02:06","outTime":"2018-01-26 02:06","id":5,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":55,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"1,2","remark":"感觉","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"b79f9426c25449ceafd50da7e6e92fab","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 00:59","outTime":"2018-01-26 00:59","id":4,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":121,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"3,2","remark":"阿丰","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"57d2ba97a15a4e8492cc7f5c6d38c5e5","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 00:58","outTime":"2018-01-26 00:58","id":3,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15958273487","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":1,"total":66,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"1,3","remark":"刚哥啊","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"83b49c2ca1bd43eaa44d4ff196bc1581","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 00:57","outTime":"2018-01-26 00:57","id":2,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15958273487","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":1,"total":66,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2,3","remark":"f ","cancelId":0,"realMoney":0,"payMoney":1,"kuponListId":null,"abnormal":0,"orderNumber":"90585e71bfdb40209b1585277e7f2bc5","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"}],"pageInfo":{"total":10,"totalPage":1,"currentPage":1}}
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
         * indentList : [{"addTime":"2018-02-06 23:32","outTime":"2018-02-08 23:32","id":18,"address":{"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15958273487","name":"个","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":3,"userId":1,"total":11,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"3,4","remark":"阿斯蒂芬","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"d6d2a692cb3d43aea12ccd89c932a8c8","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-02-04 18:45","outTime":"2018-02-06 18:45","id":14,"address":{"id":11,"province":"北京市","city":"北京市","county":"东城区","detailed":"阿斯蒂芬","phoneNumber":"15958273488","name":"阿斯蒂芬","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":11,"userId":3,"total":11,"used":0,"payStatus":0,"user":{"id":3,"phoneNumber":"15958273488","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"5","remark":"阿试","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"a17b5b28629d47e59fe2edabc86a9b28","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-02-03 17:03","outTime":"2018-02-05 17:03","id":13,"address":{"id":11,"province":"北京市","city":"北京市","county":"东城区","detailed":"阿斯蒂芬","phoneNumber":"15958273488","name":"阿斯蒂芬","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":11,"userId":3,"total":22,"used":0,"payStatus":0,"user":{"id":3,"phoneNumber":"15958273488","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2","remark":"发顺丰","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"973a828b856a4e2ba214e8780d4623c0","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-02-03 16:21","outTime":"2018-02-05 16:21","id":12,"address":{"id":0,"province":null,"city":null,"county":null,"detailed":null,"phoneNumber":"15958273488","name":null,"userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":4,"userId":3,"total":33,"used":0,"payStatus":0,"user":{"id":3,"phoneNumber":"15958273488","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"1,2","remark":"asdf","cancelId":0,"realMoney":-1,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"bdda559ce64b4bca9154d023e98a5b79","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 02:14","outTime":"2018-01-26 02:14","id":8,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":11,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2","remark":"1","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"d91dabb4c4684f86bacc9d06c442e3b9","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 02:13","outTime":"2018-01-26 02:13","id":7,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":11,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2","remark":"1","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"180fd661aa604c8196c408ec78c43f02","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 02:06","outTime":"2018-01-26 02:06","id":5,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":55,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"1,2","remark":"感觉","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"b79f9426c25449ceafd50da7e6e92fab","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 00:59","outTime":"2018-01-26 00:59","id":4,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15888888888","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":2,"total":121,"used":0,"payStatus":0,"user":{"id":2,"phoneNumber":"15888888888","password":null,"openid":null,"userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":null,"addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"3,2","remark":"阿丰","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"57d2ba97a15a4e8492cc7f5c6d38c5e5","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 00:58","outTime":"2018-01-26 00:58","id":3,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15958273487","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":1,"total":66,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"1,3","remark":"刚哥啊","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"83b49c2ca1bd43eaa44d4ff196bc1581","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"},{"addTime":"2018-01-24 00:57","outTime":"2018-01-26 00:57","id":2,"address":{"id":2,"province":"北京市","city":"北京市","county":"东城区","detailed":"放个","phoneNumber":"15958273487","name":"啊","userId":0,"status":0,"usersex":0,"isetw":0},"status":1,"combos":null,"addressId":2,"userId":1,"total":66,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2,3","remark":"f ","cancelId":0,"realMoney":0,"payMoney":1,"kuponListId":null,"abnormal":0,"orderNumber":"90585e71bfdb40209b1585277e7f2bc5","hangersId":null,"startHanger":0,"endHanger":0,"indentDress":null,"barCode":null,"payWay":"公众号支付"}]
         * pageInfo : {"total":10,"totalPage":1,"currentPage":1}
         */

        private PageInfoEntity pageInfo;
        private List<IndentListEntity> indentList;

        public void setPageInfo(PageInfoEntity pageInfo) {
            this.pageInfo = pageInfo;
        }

        public void setIndentList(List<IndentListEntity> indentList) {
            this.indentList = indentList;
        }

        public PageInfoEntity getPageInfo() {
            return pageInfo;
        }

        public List<IndentListEntity> getIndentList() {
            return indentList;
        }

        public static class PageInfoEntity {
            /**
             * total : 10
             * totalPage : 1
             * currentPage : 1
             */

            private int total;
            private int totalPage;
            private int currentPage;

            public void setTotal(int total) {
                this.total = total;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getTotal() {
                return total;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public int getCurrentPage() {
                return currentPage;
            }
        }

        public static class IndentListEntity {
            /**
             * addTime : 2018-02-06 23:32
             * outTime : 2018-02-08 23:32
             * id : 18
             * address : {"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15958273487","name":"个","userId":0,"status":0,"usersex":0,"isetw":0}
             * status : 1
             * combos : null
             * addressId : 3
             * userId : 1
             * total : 11.0
             * used : 0
             * payStatus : 0
             * user : {"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null}
             * employeeId : 0
             * employee : {"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null}
             * serviceIds : 3,4
             * remark : 阿斯蒂芬
             * cancelId : 0
             * realMoney : -1.0
             * payMoney : 0.0
             * kuponListId : null
             * abnormal : 0
             * orderNumber : d6d2a692cb3d43aea12ccd89c932a8c8
             * hangersId : null
             * startHanger : 0
             * endHanger : 0
             * indentDress : null
             * barCode : null
             * payWay : 公众号支付
             */

            private String addTime;
            private String outTime;
            private int id;
            private AddressEntity address;
            private int status;
            private Object combos;
            private int addressId;
            private int userId;
            private double total;
            private int used;
            private int payStatus;
            private UserEntity user;
            private int employeeId;
            private EmployeeEntity employee;
            private String serviceIds;
            private String remark;
            private int cancelId;
            private double realMoney;
            private double payMoney;
            private Object kuponListId;
            private int abnormal;
            private String orderNumber;
            private Object hangersId;
            private int startHanger;
            private int endHanger;
            private Object indentDress;
            private Object barCode;
            private String payWay;
            private String estateName;
            private String detailed;
            private int estateId;

            public String getDetailed() {
                return detailed;
            }

            public void setDetailed(String detailed) {
                this.detailed = detailed;
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

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public void setOutTime(String outTime) {
                this.outTime = outTime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setAddress(AddressEntity address) {
                this.address = address;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setCombos(Object combos) {
                this.combos = combos;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setTotal(double total) {
                this.total = total;
            }

            public void setUsed(int used) {
                this.used = used;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
            }

            public void setEmployee(EmployeeEntity employee) {
                this.employee = employee;
            }

            public void setServiceIds(String serviceIds) {
                this.serviceIds = serviceIds;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setCancelId(int cancelId) {
                this.cancelId = cancelId;
            }

            public void setRealMoney(double realMoney) {
                this.realMoney = realMoney;
            }

            public void setPayMoney(double payMoney) {
                this.payMoney = payMoney;
            }

            public void setKuponListId(Object kuponListId) {
                this.kuponListId = kuponListId;
            }

            public void setAbnormal(int abnormal) {
                this.abnormal = abnormal;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public void setHangersId(Object hangersId) {
                this.hangersId = hangersId;
            }

            public void setStartHanger(int startHanger) {
                this.startHanger = startHanger;
            }

            public void setEndHanger(int endHanger) {
                this.endHanger = endHanger;
            }

            public void setIndentDress(Object indentDress) {
                this.indentDress = indentDress;
            }

            public void setBarCode(Object barCode) {
                this.barCode = barCode;
            }

            public void setPayWay(String payWay) {
                this.payWay = payWay;
            }

            public String getAddTime() {
                return addTime;
            }

            public String getOutTime() {
                return outTime;
            }

            public int getId() {
                return id;
            }

            public AddressEntity getAddress() {
                return address;
            }

            public int getStatus() {
                return status;
            }

            public Object getCombos() {
                return combos;
            }

            public int getAddressId() {
                return addressId;
            }

            public int getUserId() {
                return userId;
            }

            public double getTotal() {
                return total;
            }

            public int getUsed() {
                return used;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public UserEntity getUser() {
                return user;
            }

            public int getEmployeeId() {
                return employeeId;
            }

            public EmployeeEntity getEmployee() {
                return employee;
            }

            public String getServiceIds() {
                return serviceIds;
            }

            public String getRemark() {
                return remark;
            }

            public int getCancelId() {
                return cancelId;
            }

            public double getRealMoney() {
                return realMoney;
            }

            public double getPayMoney() {
                return payMoney;
            }

            public Object getKuponListId() {
                return kuponListId;
            }

            public int getAbnormal() {
                return abnormal;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public Object getHangersId() {
                return hangersId;
            }

            public int getStartHanger() {
                return startHanger;
            }

            public int getEndHanger() {
                return endHanger;
            }

            public Object getIndentDress() {
                return indentDress;
            }

            public Object getBarCode() {
                return barCode;
            }

            public String getPayWay() {
                return payWay;
            }

            public static class AddressEntity {
                /**
                 * id : 3
                 * province : 北京市
                 * city : 北京市
                 * county : 东城区
                 * detailed : 个回合
                 * phoneNumber : 15958273487
                 * name : 个
                 * userId : 0
                 * status : 0
                 * usersex : 0
                 * isetw : 0
                 */

                private int id;
                private String province;
                private String city;
                private String county;
                private String detailed;
                private String phoneNumber;
                private String name;
                private int userId;
                private int status;
                private int usersex;
                private int isetw;

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
                    this.name = name;
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
                    return name;
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

            public static class UserEntity {
                /**
                 * id : 1
                 * phoneNumber : 15958273487
                 * password : null
                 * openid : o8mxc0cNSLDY4lz68pqVhczWdqns
                 * userName : null
                 * loginName : null
                 * integral : 0
                 * balance : 0.0
                 * addressid : null
                 * imgurl : null
                 * registrationId : 140fe1da9ea1f88e400
                 * addTime : null
                 * token : null
                 */

                private int id;
                private String phoneNumber;
                private Object password;
                private String openid;
                private Object userName;
                private Object loginName;
                private int integral;
                private double balance;
                private Object addressid;
                private Object imgurl;
                private String registrationId;
                private Object addTime;
                private Object token;

                public void setId(int id) {
                    this.id = id;
                }

                public void setPhoneNumber(String phoneNumber) {
                    this.phoneNumber = phoneNumber;
                }

                public void setPassword(Object password) {
                    this.password = password;
                }

                public void setOpenid(String openid) {
                    this.openid = openid;
                }

                public void setUserName(Object userName) {
                    this.userName = userName;
                }

                public void setLoginName(Object loginName) {
                    this.loginName = loginName;
                }

                public void setIntegral(int integral) {
                    this.integral = integral;
                }

                public void setBalance(double balance) {
                    this.balance = balance;
                }

                public void setAddressid(Object addressid) {
                    this.addressid = addressid;
                }

                public void setImgurl(Object imgurl) {
                    this.imgurl = imgurl;
                }

                public void setRegistrationId(String registrationId) {
                    this.registrationId = registrationId;
                }

                public void setAddTime(Object addTime) {
                    this.addTime = addTime;
                }

                public void setToken(Object token) {
                    this.token = token;
                }

                public int getId() {
                    return id;
                }

                public String getPhoneNumber() {
                    return phoneNumber;
                }

                public Object getPassword() {
                    return password;
                }

                public String getOpenid() {
                    return openid;
                }

                public Object getUserName() {
                    return userName;
                }

                public Object getLoginName() {
                    return loginName;
                }

                public int getIntegral() {
                    return integral;
                }

                public double getBalance() {
                    return balance;
                }

                public Object getAddressid() {
                    return addressid;
                }

                public Object getImgurl() {
                    return imgurl;
                }

                public String getRegistrationId() {
                    return registrationId;
                }

                public Object getAddTime() {
                    return addTime;
                }

                public Object getToken() {
                    return token;
                }
            }

            public static class EmployeeEntity {
                /**
                 * id : 0
                 * employeeName : null
                 * employeePhone : null
                 * employeeEmail : null
                 * employeeAddress : null
                 * employeeLevel : 0
                 * employeePassword : null
                 * employeeToken : null
                 */

                private int id;
                private Object employeeName;
                private Object employeePhone;
                private Object employeeEmail;
                private Object employeeAddress;
                private int employeeLevel;
                private Object employeePassword;
                private Object employeeToken;

                public void setId(int id) {
                    this.id = id;
                }

                public void setEmployeeName(Object employeeName) {
                    this.employeeName = employeeName;
                }

                public void setEmployeePhone(Object employeePhone) {
                    this.employeePhone = employeePhone;
                }

                public void setEmployeeEmail(Object employeeEmail) {
                    this.employeeEmail = employeeEmail;
                }

                public void setEmployeeAddress(Object employeeAddress) {
                    this.employeeAddress = employeeAddress;
                }

                public void setEmployeeLevel(int employeeLevel) {
                    this.employeeLevel = employeeLevel;
                }

                public void setEmployeePassword(Object employeePassword) {
                    this.employeePassword = employeePassword;
                }

                public void setEmployeeToken(Object employeeToken) {
                    this.employeeToken = employeeToken;
                }

                public int getId() {
                    return id;
                }

                public Object getEmployeeName() {
                    return employeeName;
                }

                public Object getEmployeePhone() {
                    return employeePhone;
                }

                public Object getEmployeeEmail() {
                    return employeeEmail;
                }

                public Object getEmployeeAddress() {
                    return employeeAddress;
                }

                public int getEmployeeLevel() {
                    return employeeLevel;
                }

                public Object getEmployeePassword() {
                    return employeePassword;
                }

                public Object getEmployeeToken() {
                    return employeeToken;
                }
            }
        }
    }
}
