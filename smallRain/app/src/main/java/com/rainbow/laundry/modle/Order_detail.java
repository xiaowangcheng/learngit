package com.rainbow.laundry.modle;

import java.util.List;

/**
 * Created by wyc on 2018/1/2.
 */

public class Order_detail  extends Base{


    /**
     * status : 1
     * msg : null
     * data : {"indent":{"addTime":"2018-02-06 09:42","outTime":"2018-02-08 09:42","id":15,"address":{"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15958273487","name":"个","userId":0,"status":0,"usersex":0,"isetw":0},"status":-2,"combos":null,"addressId":3,"userId":1,"total":22,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"Integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2,3,4","remark":"好好洗","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"5a70e2bde1fd4ac88af5ba9f2b03aff8","hangersId":1,"startHanger":32,"endHanger":37,"indentDress":"","barCode":"999999","payWay":"公众号支付"},"comboList":[{"id":23,"indentid":15,"comboid":1,"count":1,"combo":{"id":1,"sort":1,"classes":11,"price":11,"comboname":"阿斯蒂芬","imgurl":"/upload/xzcplhyydx4nheadimg.png","specify":"四方达","remark":"阿斯蒂芬","secondary":"啊法伤发","introduce":"阿发啊都是","status":0,"count":1}},{"id":24,"indentid":15,"comboid":7,"count":1,"combo":{"id":7,"sort":1,"classes":11,"price":11,"comboname":"阿萨德","imgurl":"/upload/irvwyfebbpybheadimg.png","specify":"阿斯蒂芬","remark":"艾弗森","secondary":"沙发","introduce":"阿斯蒂芬","status":0,"count":1}}],"kuponList":[{"id":1,"userId":1,"kuponId":1,"kupon":{"id":0,"fordrn":0,"agio":0,"startTime":null,"endTime":null,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null},{"id":2,"userId":1,"kuponId":2,"kupon":{"id":0,"fordrn":0,"agio":0,"startTime":null,"endTime":null,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null}]}
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
         * indent : {"addTime":"2018-02-06 09:42","outTime":"2018-02-08 09:42","id":15,"address":{"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15958273487","name":"个","userId":0,"status":0,"usersex":0,"isetw":0},"status":-2,"combos":null,"addressId":3,"userId":1,"total":22,"used":0,"payStatus":0,"user":{"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"Integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null},"employeeId":0,"employee":{"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null},"serviceIds":"2,3,4","remark":"好好洗","cancelId":0,"realMoney":0,"payMoney":0,"kuponListId":null,"abnormal":0,"orderNumber":"5a70e2bde1fd4ac88af5ba9f2b03aff8","hangersId":1,"startHanger":32,"endHanger":37,"indentDress":"","barCode":"999999","payWay":"公众号支付"}
         * comboList : [{"id":23,"indentid":15,"comboid":1,"count":1,"combo":{"id":1,"sort":1,"classes":11,"price":11,"comboname":"阿斯蒂芬","imgurl":"/upload/xzcplhyydx4nheadimg.png","specify":"四方达","remark":"阿斯蒂芬","secondary":"啊法伤发","introduce":"阿发啊都是","status":0,"count":1}},{"id":24,"indentid":15,"comboid":7,"count":1,"combo":{"id":7,"sort":1,"classes":11,"price":11,"comboname":"阿萨德","imgurl":"/upload/irvwyfebbpybheadimg.png","specify":"阿斯蒂芬","remark":"艾弗森","secondary":"沙发","introduce":"阿斯蒂芬","status":0,"count":1}}]
         * kuponList : [{"id":1,"userId":1,"kuponId":1,"kupon":{"id":0,"fordrn":0,"agio":0,"startTime":null,"endTime":null,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null},{"id":2,"userId":1,"kuponId":2,"kupon":{"id":0,"fordrn":0,"agio":0,"startTime":null,"endTime":null,"status":0,"addTime":null},"status":0,"overdueTime":null,"addTime":1516723200000,"useTime":null}]
         */

        private IndentEntity indent;
        private List<ComboListEntity> comboList;
        private List<KuponListEntity> kuponList;

        public void setIndent(IndentEntity indent) {
            this.indent = indent;
        }

        public void setComboList(List<ComboListEntity> comboList) {
            this.comboList = comboList;
        }

        public void setKuponList(List<KuponListEntity> kuponList) {
            this.kuponList = kuponList;
        }

        public IndentEntity getIndent() {
            return indent;
        }

        public List<ComboListEntity> getComboList() {
            return comboList;
        }

        public List<KuponListEntity> getKuponList() {
            return kuponList;
        }

        public static class IndentEntity {
            /**
             * addTime : 2018-02-06 09:42
             * outTime : 2018-02-08 09:42
             * id : 15
             * address : {"id":3,"province":"北京市","city":"北京市","county":"东城区","detailed":"个回合","phoneNumber":"15958273487","name":"个","userId":0,"status":0,"usersex":0,"isetw":0}
             * status : -2
             * combos : null
             * addressId : 3
             * userId : 1
             * total : 22.0
             * used : 0
             * payStatus : 0
             * user : {"id":1,"phoneNumber":"15958273487","password":null,"openid":"o8mxc0cNSLDY4lz68pqVhczWdqns","userName":null,"loginName":null,"Integral":0,"balance":0,"addressid":null,"imgurl":null,"registrationId":"140fe1da9ea1f88e400","addTime":null,"token":null}
             * employeeId : 0
             * employee : {"id":0,"employeeName":null,"employeePhone":null,"employeeEmail":null,"employeeAddress":null,"employeeLevel":0,"employeePassword":null,"employeeToken":null}
             * serviceIds : 2,3,4
             * remark : 好好洗
             * cancelId : 0
             * realMoney : 0.0
             * payMoney : 0.0
             * kuponListId : null
             * abnormal : 0
             * orderNumber : 5a70e2bde1fd4ac88af5ba9f2b03aff8
             * hangersId : 1
             * startHanger : 32
             * endHanger : 37
             * indentDress :
             * barCode : 999999
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
            private int hangersId;
            private int startHanger;
            private int endHanger;
            private String indentDress;
            private String barCode;
            private String payWay;

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

            public void setHangersId(int hangersId) {
                this.hangersId = hangersId;
            }

            public void setStartHanger(int startHanger) {
                this.startHanger = startHanger;
            }

            public void setEndHanger(int endHanger) {
                this.endHanger = endHanger;
            }

            public void setIndentDress(String indentDress) {
                this.indentDress = indentDress;
            }

            public void setBarCode(String barCode) {
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

            public int getHangersId() {
                return hangersId;
            }

            public int getStartHanger() {
                return startHanger;
            }

            public int getEndHanger() {
                return endHanger;
            }

            public String getIndentDress() {
                return indentDress;
            }

            public String getBarCode() {
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
                 * Integral : 0
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

        public static class ComboListEntity {
            /**
             * id : 23
             * indentid : 15
             * comboid : 1
             * count : 1
             * combo : {"id":1,"sort":1,"classes":11,"price":11,"comboname":"阿斯蒂芬","imgurl":"/upload/xzcplhyydx4nheadimg.png","specify":"四方达","remark":"阿斯蒂芬","secondary":"啊法伤发","introduce":"阿发啊都是","status":0,"count":1}
             */

            private int id;
            private int indentid;
            private int comboid;
            private int count;
            private ComboEntity combo;

            public void setId(int id) {
                this.id = id;
            }

            public void setIndentid(int indentid) {
                this.indentid = indentid;
            }

            public void setComboid(int comboid) {
                this.comboid = comboid;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setCombo(ComboEntity combo) {
                this.combo = combo;
            }

            public int getId() {
                return id;
            }

            public int getIndentid() {
                return indentid;
            }

            public int getComboid() {
                return comboid;
            }

            public int getCount() {
                return count;
            }

            public ComboEntity getCombo() {
                return combo;
            }

            public static class ComboEntity {
                /**
                 * id : 1
                 * sort : 1
                 * classes : 11
                 * price : 11.0
                 * comboname : 阿斯蒂芬
                 * imgurl : /upload/xzcplhyydx4nheadimg.png
                 * specify : 四方达
                 * remark : 阿斯蒂芬
                 * secondary : 啊法伤发
                 * introduce : 阿发啊都是
                 * status : 0
                 * count : 1
                 */

                private int id;
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

                public void setId(int id) {
                    this.id = id;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public void setClasses(int classes) {
                    this.classes = classes;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public void setComboname(String comboname) {
                    this.comboname = comboname;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public void setSpecify(String specify) {
                    this.specify = specify;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public void setSecondary(String secondary) {
                    this.secondary = secondary;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getId() {
                    return id;
                }

                public int getSort() {
                    return sort;
                }

                public int getClasses() {
                    return classes;
                }

                public double getPrice() {
                    return price;
                }

                public String getComboname() {
                    return comboname;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public String getSpecify() {
                    return specify;
                }

                public String getRemark() {
                    return remark;
                }

                public String getSecondary() {
                    return secondary;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public int getStatus() {
                    return status;
                }

                public int getCount() {
                    return count;
                }
            }
        }

        public static class KuponListEntity {
            /**
             * id : 1
             * userId : 1
             * kuponId : 1
             * kupon : {"id":0,"fordrn":0,"agio":0,"startTime":null,"endTime":null,"status":0,"addTime":null}
             * status : 0
             * overdueTime : null
             * addTime : 1516723200000
             * useTime : null
             */

            private int id;
            private int userId;
            private int kuponId;
            private KuponEntity kupon;
            private int status;
            private Object overdueTime;
            private long addTime;
            private Object useTime;

            public void setId(int id) {
                this.id = id;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setKuponId(int kuponId) {
                this.kuponId = kuponId;
            }

            public void setKupon(KuponEntity kupon) {
                this.kupon = kupon;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setOverdueTime(Object overdueTime) {
                this.overdueTime = overdueTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public void setUseTime(Object useTime) {
                this.useTime = useTime;
            }

            public int getId() {
                return id;
            }

            public int getUserId() {
                return userId;
            }

            public int getKuponId() {
                return kuponId;
            }

            public KuponEntity getKupon() {
                return kupon;
            }

            public int getStatus() {
                return status;
            }

            public Object getOverdueTime() {
                return overdueTime;
            }

            public long getAddTime() {
                return addTime;
            }

            public Object getUseTime() {
                return useTime;
            }

            public static class KuponEntity {
                /**
                 * id : 0
                 * fordrn : 0.0
                 * agio : 0.0
                 * startTime : null
                 * endTime : null
                 * status : 0
                 * addTime : null
                 */

                private int id;
                private double fordrn;
                private double agio;
                private Object startTime;
                private Object endTime;
                private int status;
                private Object addTime;

                public void setId(int id) {
                    this.id = id;
                }

                public void setFordrn(double fordrn) {
                    this.fordrn = fordrn;
                }

                public void setAgio(double agio) {
                    this.agio = agio;
                }

                public void setStartTime(Object startTime) {
                    this.startTime = startTime;
                }

                public void setEndTime(Object endTime) {
                    this.endTime = endTime;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public void setAddTime(Object addTime) {
                    this.addTime = addTime;
                }

                public int getId() {
                    return id;
                }

                public double getFordrn() {
                    return fordrn;
                }

                public double getAgio() {
                    return agio;
                }

                public Object getStartTime() {
                    return startTime;
                }

                public Object getEndTime() {
                    return endTime;
                }

                public int getStatus() {
                    return status;
                }

                public Object getAddTime() {
                    return addTime;
                }
            }
        }
    }
}
