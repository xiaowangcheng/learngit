package com.manage.rain.model;

import java.util.List;

/**
 * Created by wyf on 2018/1/6.
 */

public class OrderDetial extends Base{


    /**
     * status : 1
     * msg : null
     * data : {"indent":{"id":"91961534499846f3b70c7448bda6951c","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userid":"dddsadasd","total":11,"used":0,"address":{"addressId":"b5faeb7ba1624630a215c38fe8d62561","province":null,"city":"宁波","county":"镇海区","detailed":"不用知道","phonenumber":"18720147511","name":"郝顺","userid":null,"status":0,"usersex":0,"isetw":0},"addTime":1511517055000,"paystatus":0,"user":{"userid":"dddsadasd","phonenumber":"18720147511","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressId":null,"imgurl":null},"employeeId":null,"employee":null,"serviceids":"1,2","remark":null,"cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null},"employees":[{"employeeId":"292e34f069974d63a09a03e3de1f0b44","employeeName":"阿丰","employeePhone":"15858585859","employeeEmail":"22@qq.com","employeeAddress":"阿道夫","employeeLevel":0,"employeePassword":"111111"},{"employeeId":"7a97761bbf9941198ad05d70d9e82ecd","employeeName":"发个","employeePhone":"15252525253","employeeEmail":"asdf@qq.com","employeeAddress":"安抚顾客","employeeLevel":0,"employeePassword":"111111"},{"employeeId":"c1917f8362a047bb95f3c3f03b827840","employeeName":"发的","employeePhone":"15858585858","employeeEmail":"222@qq.com","employeeAddress":"阿斯蒂芬","employeeLevel":3,"employeePassword":"111111"},{"employeeId":"dde358ab6b8b4da08fa904ae299b6073","employeeName":"阿丰","employeePhone":"15252525252","employeeEmail":null,"employeeAddress":"大发发","employeeLevel":1,"employeePassword":"111111"},{"employeeId":"efcc0666b01f4c919effe2cf0e83ff4c","employeeName":"阿斯蒂芬","employeePhone":"15858585857","employeeEmail":"wea@qq.com","employeeAddress":"大发接口来豆腐","employeeLevel":0,"employeePassword":"111111"}]}
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
         * indent : {"id":"91961534499846f3b70c7448bda6951c","status":1,"combos":null,"addressId":"b5faeb7ba1624630a215c38fe8d62561","userid":"dddsadasd","total":11,"used":0,"address":{"addressId":"b5faeb7ba1624630a215c38fe8d62561","province":null,"city":"宁波","county":"镇海区","detailed":"不用知道","phonenumber":"18720147511","name":"郝顺","userid":null,"status":0,"usersex":0,"isetw":0},"addTime":1511517055000,"paystatus":0,"user":{"userid":"dddsadasd","phonenumber":"18720147511","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressId":null,"imgurl":null},"employeeId":null,"employee":null,"serviceids":"1,2","remark":null,"cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null}
         * employees : [{"employeeId":"292e34f069974d63a09a03e3de1f0b44","employeeName":"阿丰","employeePhone":"15858585859","employeeEmail":"22@qq.com","employeeAddress":"阿道夫","employeeLevel":0,"employeePassword":"111111"},{"employeeId":"7a97761bbf9941198ad05d70d9e82ecd","employeeName":"发个","employeePhone":"15252525253","employeeEmail":"asdf@qq.com","employeeAddress":"安抚顾客","employeeLevel":0,"employeePassword":"111111"},{"employeeId":"c1917f8362a047bb95f3c3f03b827840","employeeName":"发的","employeePhone":"15858585858","employeeEmail":"222@qq.com","employeeAddress":"阿斯蒂芬","employeeLevel":3,"employeePassword":"111111"},{"employeeId":"dde358ab6b8b4da08fa904ae299b6073","employeeName":"阿丰","employeePhone":"15252525252","employeeEmail":null,"employeeAddress":"大发发","employeeLevel":1,"employeePassword":"111111"},{"employeeId":"efcc0666b01f4c919effe2cf0e83ff4c","employeeName":"阿斯蒂芬","employeePhone":"15858585857","employeeEmail":"wea@qq.com","employeeAddress":"大发接口来豆腐","employeeLevel":0,"employeePassword":"111111"}]
         */

        private IndentEntity indent;
        private List<EmployeesEntity> employees;

        public void setIndent(IndentEntity indent) {
            this.indent = indent;
        }

        public void setEmployees(List<EmployeesEntity> employees) {
            this.employees = employees;
        }

        public IndentEntity getIndent() {
            return indent;
        }

        public List<EmployeesEntity> getEmployees() {
            return employees;
        }

        public static class IndentEntity {
            /**
             * id : 91961534499846f3b70c7448bda6951c
             * status : 1
             * combos : null
             * addressId : b5faeb7ba1624630a215c38fe8d62561
             * userid : dddsadasd
             * total : 11.0
             * used : 0
             * address : {"addressId":"b5faeb7ba1624630a215c38fe8d62561","province":null,"city":"宁波","county":"镇海区","detailed":"不用知道","phonenumber":"18720147511","name":"郝顺","userid":null,"status":0,"usersex":0,"isetw":0}
             * addTime : 1511517055000
             * paystatus : 0
             * user : {"userid":"dddsadasd","phonenumber":"18720147511","password":null,"openid":null,"username":null,"loginname":null,"integral":0,"balance":0,"addressId":null,"imgurl":null}
             * employeeId : null
             * employee : null
             * serviceids : 1,2
             * remark : null
             * cancelId : 0
             * realMoney : 0.0
             * payMoney : 0.0
             * kuponListId : null
             *
             * userEstateId  用户小区id
             * estateId      小区id
             * estateName    小区地址
             * detailed      详细门牌
             * userSex       性别
             * userSexStr
             * phoneNumber   手机号
             * conName       联系人姓名
             */

            private int  userEstateId;
            private int  estateId;
            private String estateName;
            private String detailed;
            private String userSex;
            private String userSexStr;
            private String phoneNumber;
            private String conName;


            private UserEntity user;
            private AddressEntity address;
            private Object employee;

            private String addTime;
            private String outTime;
            private String id;
            private int status;
            private Object combos;
            private int addressId;
            private int userId;
            private double total;
            private int used;
            private int payStatus;

            private int employeeId;
            private String serviceIds;
            private String remark;
            private int cancelId;
            private double realMoney;
            private double payMoney;
            private int kuponListId;
            private int abnormal;
            private String orderNumber;
            private int hangersId;
            private int startHanger;
            private int endHanger;
            private String indentDress;
            private String barCode;
            private String payWay;

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

            public String getUserSex() {
                return userSex;
            }

            public void setUserSex(String userSex) {
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

            public String getConName() {
                return conName;
            }

            public void setConName(String conName) {
                this.conName = conName;
            }

            public UserEntity getUser() {
                return user;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public AddressEntity getAddress() {
                return address;
            }

            public void setAddress(AddressEntity address) {
                this.address = address;
            }

            public Object getEmployee() {
                return employee;
            }

            public void setEmployee(Object employee) {
                this.employee = employee;
            }

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

            public Object getCombos() {
                return combos;
            }

            public void setCombos(Object combos) {
                this.combos = combos;
            }

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
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

            public int getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
            }

            public String getServiceIds() {
                return serviceIds;
            }

            public void setServiceIds(String serviceIds) {
                this.serviceIds = serviceIds;
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

            public String getIndentDress() {
                return indentDress;
            }

            public void setIndentDress(String indentDress) {
                this.indentDress = indentDress;
            }

            public String getBarCode() {
                return barCode;
            }

            public void setBarCode(String barCode) {
                this.barCode = barCode;
            }

            public String getPayWay() {
                return payWay;
            }

            public void setPayWay(String payWay) {
                this.payWay = payWay;
            }

            public static class AddressEntity {
                /**
                 * addressId : b5faeb7ba1624630a215c38fe8d62561
                 * province : null
                 * city : 宁波
                 * county : 镇海区
                 * detailed : 不用知道
                 * phonenumber : 18720147511
                 * name : 郝顺
                 * userid : null
                 * status : 0
                 * usersex : 0
                 * isetw : 0
                 */

                private String addressid;
                private Object province;
                private String city;
                private String county;
                private String detailed;
                private String phonenumber;
                private String name;
                private Object userid;
                private int status;
                private int usersex;
                private int isetw;

                public void setAddressid(String addressid) {
                    this.addressid = addressid;
                }

                public void setProvince(Object province) {
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

                public void setUserid(Object userid) {
                    this.userid = userid;
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

                public String getAddressid() {
                    return addressid;
                }

                public Object getProvince() {
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

                public Object getUserid() {
                    return userid;
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
                 * userid : dddsadasd
                 * phonenumber : 18720147511
                 * password : null
                 * openid : null
                 * username : null
                 * loginname : null
                 * integral : 0
                 * balance : 0.0
                 * addressId : null
                 * imgurl : null
                 */

                private String userid;
                private String phonenumber;
                private Object password;
                private Object openid;
                private Object username;
                private Object loginname;
                private int integral;
                private double balance;
                private Object addressid;
                private Object imgurl;

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public void setPhonenumber(String phonenumber) {
                    this.phonenumber = phonenumber;
                }

                public void setPassword(Object password) {
                    this.password = password;
                }

                public void setOpenid(Object openid) {
                    this.openid = openid;
                }

                public void setUsername(Object username) {
                    this.username = username;
                }

                public void setLoginname(Object loginname) {
                    this.loginname = loginname;
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

                public String getUserid() {
                    return userid;
                }

                public String getPhonenumber() {
                    return phonenumber;
                }

                public Object getPassword() {
                    return password;
                }

                public Object getOpenid() {
                    return openid;
                }

                public Object getUsername() {
                    return username;
                }

                public Object getLoginname() {
                    return loginname;
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
            }
        }

        public static class EmployeesEntity {
            /**
             * employeeId : 292e34f069974d63a09a03e3de1f0b44
             * employeeName : 阿丰
             * employeePhone : 15858585859
             * employeeEmail : 22@qq.com
             * employeeAddress : 阿道夫
             * employeeLevel : 0
             * employeePassword : 111111
             */
            private int selectIndxe =0;
            private String employeeId;
            private String employeeName;
            private String employeePhone;
            private String employeeEmail;
            private String employeeAddress;
            private int employeeLevel;
            private String employeePassword;

            public int getSelectIndxe() {
                return selectIndxe;
            }

            public void setSelectIndxe(int selectIndxe) {
                this.selectIndxe = selectIndxe;
            }

            public void setEmployeeId(String employeeId) {
                this.employeeId = employeeId;
            }

            public void setEmployeeName(String employeeName) {
                this.employeeName = employeeName;
            }

            public void setEmployeePhone(String employeePhone) {
                this.employeePhone = employeePhone;
            }

            public void setEmployeeEmail(String employeeEmail) {
                this.employeeEmail = employeeEmail;
            }

            public void setEmployeeAddress(String employeeAddress) {
                this.employeeAddress = employeeAddress;
            }

            public void setEmployeeLevel(int employeeLevel) {
                this.employeeLevel = employeeLevel;
            }

            public void setEmployeePassword(String employeePassword) {
                this.employeePassword = employeePassword;
            }

            public String getEmployeeId() {
                return employeeId;
            }

            public String getEmployeeName() {
                return employeeName;
            }

            public String getEmployeePhone() {
                return employeePhone;
            }

            public String getEmployeeEmail() {
                return employeeEmail;
            }

            public String getEmployeeAddress() {
                return employeeAddress;
            }

            public int getEmployeeLevel() {
                return employeeLevel;
            }

            public String getEmployeePassword() {
                return employeePassword;
            }
        }
    }
}
