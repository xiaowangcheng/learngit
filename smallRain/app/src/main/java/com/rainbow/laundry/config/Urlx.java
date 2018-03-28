package com.rainbow.laundry.config;

/**
 * Created by wyf on 2018/1/2.
 */

public class Urlx {
    //public static final String Host="http://121.196.201.224/cleanserver";//服务器
    public static final String Host="http://www.xiaoyudian8.com";  //生产
    //public static final String Host="http://192.168.99.144:8080";

    public static final String LOGIN=Host+"/user/checklogin.do";
    public static final String SEND=Host+"/message/sendtoken.do";
    public static final String REGISTER=Host+"/user/add.do";
    public static final String SERVER=Host+"/combo/loadbysortandclasses.do";

    /*
     地址管理
     */
    //地址查询
    //public static final String doFindByUserId=Host+"/address/doFindByUserId.do";//查询地址   已废弃
    public static final String myEstate=Host+"/userEstate/myEstate.do";//查询地址
    public static  final String allEstate =Host+"/estate/allEstate.do"; //查询小区
    public static  final String addEstate =Host+"/userEstate/addEstate.do"; //添加地址
    public static final String updateEstate=Host+"/userEstate/updateEstate.do";//更新地址
    //public static final String doInsertAddresss=Host+"/address/doInsertAddresss.do";//添加地址  已废弃
    //public static final String doUpdateAddress=Host+"/address/doUpdateAddress.do";//更新地址   已废弃
    public static final String dodeleteAddress=Host+"/address/delete.do";//删除地址
    public static final String  loadAddress =Host+ "/combo/placeOrder.do";//加载默认地址
    public static final String  updateAddressDefult =Host+ "/user/updateAddressId.do";//设置默认地址


    public static final String oadByUserId=Host+"/address/oadByUserId.do";

    //查询订单接口
    public static final String indentByUser=Host+"/indent/indentByUser.do";

    //加载优惠券
    public static final String loadAllCoupon=Host+"/kuponlist/loadByUserId.do";//加载优惠券

    //一键下单
    public static  final String Place_order =Host+"/indent/add.do";
    public static  final String indentDetail =Host+"/indent/indentDetail.do";

    //查询所有消息
    public static  final String queryAllMessage =Host+"/message/loadByUserId.do";
    public static  final String queryMessageById =Host+"/Message/loadById.do";

    //返回建议
    public static  final String ridicule =Host+"/opinion/add.do";

    public static  final String mallList =Host+"/product/mallList.do";

    public static  final String productDetail =Host+"/product/productDetail.do";

    public  static  final String extralist=Host+"/extra/list.do";

}
