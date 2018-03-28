package com.rainbow.laundry.modle.address;

/**
 * Created by wyc on 2018/1/2.
 */

public class MrDistrictBe extends MrAddressBase{
    private String zipcode;

    public MrDistrictBe() {
        super();
    }

    public MrDistrictBe(String name, String zipcode) {
        super();
        this.name = name;
        this.zipcode = zipcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "DistrictModel [name=" + name + ", zipcode=" + zipcode + "]";
    }
}
