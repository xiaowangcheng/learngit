package com.manage.rain.model.address;

import java.util.List;

public class MrCityBe extends MrAddressBase{
    private List<MrDistrictBe> districtList;

    public MrCityBe() {
        super();
    }


    public List<MrDistrictBe> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<MrDistrictBe> districtList) {
        this.districtList = districtList;
    }

    @Override
    public String toString() {
        return "CityModel [name=" + name + ", districtList=" + districtList
                + "]";
    }

    public String[] toArray() {
        String[] datas;
        if (districtList != null) {
            int size = districtList.size();
            datas = new String[size];
            for (int i = 0; i < size; i++) {
                datas[i] = districtList.get(i).getName();
            }
        } else {
            datas = new String[]{""};
        }

        return datas;
    }
}
