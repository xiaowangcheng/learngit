package com.manage.rain.config;

/**
 * Created by wyf on 2018/1/4.
 */

public class Urlx {
    //http://116.62.159.95:8080/cleanserver/back/index.do
    //http://192.168.0.132/back/index.do

    //public static final String Host="http://www.xiaoyudian8.com";//服务器
     public static final String Host="http://192.168.99.144:8080"; //元丰
    //public static final String Host="http://116.62.159.95:8080/cleanserver"; //顺子

    public static final String LOGIN=Host+"/staff/checklogin.do";//登录

    public static final String indentTypes=Host+"/indent/indentTypes.do";
    public static final String ordersByPayStatus=Host+"/indent/ordersByPayStatus.do";


    public static final String listCommit=Host+"/commit/listCommit.do";

    public static final String backIndentEmployee=Host+"/indent/backIndentEmployee.do";

    public static final String updateStatus=Host+"/indent/updateStatus.do";

    public static final String abnormalForIndent=Host+"/abnormal/abnormalForIndent.do";

    public static final String addAbnormal=Host+"/abnormal/addAbnormal.do";

}


