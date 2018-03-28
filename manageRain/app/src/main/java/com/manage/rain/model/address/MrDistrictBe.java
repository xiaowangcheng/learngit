package com.manage.rain.model.address;


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
