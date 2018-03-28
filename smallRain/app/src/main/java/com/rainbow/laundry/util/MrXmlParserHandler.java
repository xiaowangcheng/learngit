package com.rainbow.laundry.util;

import com.rainbow.laundry.modle.address.MrCityBe;
import com.rainbow.laundry.modle.address.MrProvinceBe;
import com.rainbow.laundry.modle.address.MrDistrictBe;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2018/1/2.
 */

public class MrXmlParserHandler   extends DefaultHandler {
    /**
     * 存储所有的解析对象
     */
    private List<MrProvinceBe> provinceList = new ArrayList<MrProvinceBe>();

    public MrXmlParserHandler() {

    }

    public List<MrProvinceBe> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
    }

    MrProvinceBe provinceModel = new MrProvinceBe();
    MrCityBe cityModel = new MrCityBe();
    MrDistrictBe districtModel = new MrDistrictBe();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals("province")) {
            provinceModel = new MrProvinceBe();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityList(new ArrayList<MrCityBe>());
        } else if (qName.equals("city")) {
            cityModel = new MrCityBe();
            cityModel.setName(attributes.getValue(0));
            cityModel.setDistrictList(new ArrayList<MrDistrictBe>());
        } else if (qName.equals("district")) {
            districtModel = new MrDistrictBe();
            districtModel.setName(attributes.getValue(0));
            districtModel.setZipcode(attributes.getValue(1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 遇到结束标记的时候，会调用这个方法
        if (qName.equals("district")) {
            cityModel.getDistrictList().add(districtModel);
        } else if (qName.equals("city")) {
            provinceModel.getCityList().add(cityModel);
        } else if (qName.equals("province")) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }
}
