package com.rainbow.laundry.modle.address;

import java.util.List;

/**
 * Created by wyc on 2018/1/2.
 */

public class MrProvinceBe extends MrAddressBase{
    private List<MrCityBe> cityList;

    public MrProvinceBe() {
        super();
    }

    public List<MrCityBe> getCityList() {
        return cityList;
    }

    public void setCityList(List<MrCityBe> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceModel [name=" + android.R.attr.name + ", cityList=" + cityList + "]";
    }
}
