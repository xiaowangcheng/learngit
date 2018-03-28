package com.rainbow.laundry.modle.address;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wyc on 2018/1/2.
 */

public class MrAddressBe implements Serializable {
    @SerializedName("Addre_LinkPhone")
    private String phone; //手机号
    //    private String defaultname;//设为默认，默认地址
//    private int defaultcolor;//默认颜色
    @SerializedName("Addre_IsDefault")
    private String isDefault;
    /**
     * Addre_Id : 1
     * Addre_Consignee : 我爱中华
     * Addre_LinkPhone : 13000000001
     * Addre_ZipCode : 315100
     * Addre_Detail : 浙江省 宁波市 鄞州区 首南街道荣安大厦（天童南路700号）B1701
     * Addre_PId : 33
     * Addre_CityId : 2
     * Addre_AreaId : 8
     * Addre_CityCode : 330208
     * Addre_IsDefault : 1
     */

    @SerializedName("Addre_Id")
    private String id;
    @SerializedName("Addre_Consignee")
    private String consignee; //收件人
    @SerializedName("Addre_ZipCode")
    private String zipCode; //邮政编码
    @SerializedName("Addre_Detail")
    private String detailAddr;//收货地址
    @SerializedName("Addre_PId")
    private String pId;// 省份id
    @SerializedName("Addre_CityId")
    private String cityId;// 市id
    @SerializedName("Addre_AreaId")
    private String areaId;// 区id
    @SerializedName("Addre_CityCode")
    private String cityCode;// 区code

    public void setDefault(String aDefault) {
        this.isDefault = aDefault;
    }

    public String isDefault() {
        return isDefault;
    }

//    public void setDefaultcolor(int defaultcolor) {
//        this.defaultcolor = defaultcolor;
//    }
//
//    public int getDefaultcolor() {
//        return defaultcolor;
//    }
//
//    public void setDefaultname(String defaultname) {
//        this.defaultname = defaultname;
//    }
//
//    public String getDefaultname() {
//        return defaultname;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void resetData() {
        setId("0");
        setConsignee("");
        setDetailAddr("");
        setPhone("");
        setAreaId("");
        setCityCode("");
        setCityId("");
        setDefault("0");
        setPId("");
        setZipCode("");
    }
}
