package com.rainbow.laundry.ui.leftmanager.address;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.address.Address;
import com.rainbow.laundry.modle.address.Estate;
import com.rainbow.laundry.modle.address.MrAddressBase;
import com.rainbow.laundry.modle.address.MrCityBe;
import com.rainbow.laundry.modle.address.MrDistrictBe;
import com.rainbow.laundry.modle.address.MrProvinceBe;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.MrXmlParserHandler;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.util.ViewUtils;
import com.rainbow.laundry.view.wheelView.OnWheelChangedListener;
import com.rainbow.laundry.view.wheelView.WheelView;
import com.rainbow.laundry.view.wheelView.adapters.ArrayWheelAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rx.functions.Action1;

/**
 *  Created by wyf on 2018/1/9.
 */

public class AddAdressActivity extends BaseActivity implements View.OnClickListener, OnWheelChangedListener {

    WheelView mViewProvince,mViewCity,mViewDistrict;

    // 所有省
    private String[] mProvinceDatas;
    //key - 省 value - 市
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    //当前省的名称
    private String mCurrentProviceName;
    //当前市的名称
    protected String mCurrentCityName;
    //当前区的名称
    private String mCurrentDistrictName = "";
    //当前区的邮政编码
    private String mCurrentZipCode = "";
    //解析省市区的XML数据
    private List<MrProvinceBe> provinceList = null;

    private int mPrePIndex, mPreCIndex, mPreDIndex;

    private String[] curAreaData;

    private LinearLayout llRegion;
    private boolean isEditMode;//当前页面标识
    private boolean isRegion;//是否选择了区域

    private TextView tv_region_show,tv_confirm,tv_cancel;

    private Button btn_add;
    private EditText et_name,et_phone,et_address_detial;
    private Address.DataEntity address;
    private String sex="0";
    private Spinner sp_diqu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        isEditMode = getIntent().getBooleanExtra("editMode",false);
        if(isEditMode){
            address = (Address.DataEntity) getIntent().getSerializableExtra("address");
        }
        initView();
        setView();
        requestArae();

        //initProvinceDatas();
        //setAreaData();
    }

    private void initView(){
        setBack();
        setTitle(isEditMode ? "编辑地址":"新增地址");
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_address_detial = (EditText) findViewById(R.id.et_address_detial);
        btn_add = (Button) findViewById(R.id.btn_add);
        tv_region_show = (TextView) findViewById(R.id.tv_region_show);


        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        llRegion = (LinearLayout) findViewById(R.id.ll_region);
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        sp_diqu = (Spinner) findViewById(R.id.sp_diqu);
    }

    private void setView(){
        final RadioGroup rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        if(isEditMode){
            btn_add.setText("确认修改");
            et_name.setText(address.getName());
            et_phone.setText(address.getProvince());
            et_address_detial.setText(address.getDetailed());
            if(address.getUsersex() ==0){
                rg_sex.check(R.id.rb_man);
            }else {
                rg_sex.check(R.id.rb_womam);
            }
            isRegion = true;
            tv_region_show.setText(address.getProvince()+" "+ address.getCity() +" " +address.getCounty());
        }else {
            btn_add.setText("添加新地址");
        }
        btn_add.setOnClickListener(this);
        //设置区域选择监听更新
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        tv_region_show.setOnClickListener(this);

        tv_confirm.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);

        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(R.id.rb_man == rg_sex.getCheckedRadioButtonId()){
                    sex = "0";
                } else {
                    sex = "1";
                }
            }
        });
    }
    ArrayList<String> arrayList=null;
    ArrayList<String> arrIndex =null;
    private void requestArae(){
        HttpParams httpParams = new HttpParams();
        HttpUntilx.RequestInfoPost(Urlx.allEstate,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            Estate estate = JSON2Class.fromJson(s, Estate.class);
                            if(estate.getStatus()==1){
                                List<Estate.DataEntity> entityList =  estate.getData();
                                arrayList = new ArrayList<String>();
                                arrIndex =  new ArrayList<String>();
                                int  index =0;
                                for (int i=0;i<entityList.size();i++){
                                    arrayList.add(entityList.get(i).getEstateName());
                                    arrIndex.add(String.valueOf(entityList.get(i).getId()));
                                    if(address.getEstateId() ==entityList.get(i).getId()){
                                        index =i;
                                    }
                                }
                                ArrayAdapter<String> identityAdapter = new ArrayAdapter<String>(AddAdressActivity.this,
                                        R.layout.spinner, arrayList);
                                identityAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                                sp_diqu.setAdapter(identityAdapter);
                                sp_diqu.setSelection(index);

                            } else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_region_show: //所在地区
                Common.hideKeyBoard(mActivity);
                if (View.INVISIBLE == llRegion.getVisibility()) {
                    llRegion.setVisibility(View.VISIBLE);
                    btn_add.setVisibility(View.GONE);
                } else {
                    llRegion.setVisibility(View.INVISIBLE);
                    btn_add.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_confirm: //地区确认，保存数据
                isRegion = true;
                int pIndex = mViewProvince.getCurrentItem();
                int cIndex = mViewCity.getCurrentItem();
                int dIndex = mViewDistrict.getCurrentItem();
                mCurrentZipCode = provinceList.get(pIndex).getCityList().get(cIndex).getDistrictList().get(dIndex).getZipcode();
                tv_region_show.setText(mCurrentProviceName + " " + " " + mCurrentCityName + " " + mCurrentDistrictName);
                llRegion.setVisibility(View.INVISIBLE);
                btn_add.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_cancel:  //地区取消
                llRegion.setVisibility(View.INVISIBLE);
                btn_add.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_add:
                //验证内容EditText
                if (!StringUtil.isEtNull(et_name, et_phone)) {
                    ToastUtils.makeText(mActivity, "请填写完整");
                    return;
                }
//                if (!isRegion) {
//                    ToastUtils.makeText(mActivity, "请选择区域");
//                    return;
//                }
                if (et_address_detial.length() < 5) {
                    ToastUtils.makeText(mActivity, "请填写详细地址");
                    return;
                }
                requestData();
                break;
        }
    }

    private  void requestData(){
        Common.hideKeyBoard(mActivity);
        if(isEditMode){
            doUpdateAddress();
        } else {
            doInsertAddresss();
        }
    }

    private void doInsertAddresss(){
        HttpParams httpParams = new HttpParams();
//        httpParams.put("province", mCurrentProviceName);
//        httpParams.put("city", mCurrentCityName);
//        httpParams.put("county", mCurrentDistrictName);
        httpParams.put("estateId", arrIndex.get(sp_diqu.getSelectedItemPosition()));
        httpParams.put("detailed", et_address_detial.getText().toString());
        httpParams.put("phonenumber",et_phone.getText().toString() );
        httpParams.put("name", et_name.getText().toString().trim() );
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("token", MyUserInfo.getToken());
        httpParams.put("usersex", sex);
        HttpUntilx.RequestInfoPost(Urlx.addEstate,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                             if(jsonObject.optString("status").equals("1")){
                                ToastUtils.makeText(mActivity,"添加成功！");
                                 setResult(ViewUtils.ADD_ADDMSG);
                                 AddAdressActivity.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
    /**
     * 地址修改
     */
    private void doUpdateAddress(){
        HttpParams httpParams = new HttpParams();
//        httpParams.put("addressid", address.getId());
//        httpParams.put("province", mCurrentProviceName);
//        httpParams.put("city", mCurrentCityName);
        httpParams.put("userEstateId", address.getId());
        httpParams.put("estateId", arrIndex.get(sp_diqu.getSelectedItemPosition()));
        httpParams.put("county", mCurrentDistrictName);
        httpParams.put("detailed", et_address_detial.getText().toString());
        httpParams.put("phonenumber", et_phone.getText().toString());
        httpParams.put("conName", et_name.getText().toString().trim());
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("token", MyUserInfo.getToken());
        httpParams.put("usersex", sex);
        HttpUntilx.RequestInfoPost(Urlx.updateEstate,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if(jsonObject.optString("status").equals("1")){
                                ToastUtils.makeText(mActivity,"修改成功！");
                                setResult(ViewUtils.ADD_ADDMSG);
                                AddAdressActivity.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    /**
     * 设置区域选择数据初始化
     */
    private void setAreaData() {
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mActivity, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        mViewProvince.setCurrentItem(mPrePIndex);
        updateCities(mPreCIndex);
        updateAreas(mPreDIndex);
    }
    /**
     * 根据当前的省，更新市WheelView的信息
     *
     * @param mPreCIndex
     */
    private void updateCities(int mPreCIndex) {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(mPreCIndex > -1 ? mPreCIndex : 0);

        updateAreas(-1);
    }
    /**
     * 根据当前的市，更新区WheelView的信息
     *
     * @param mPreDIndex
     */
    private void updateAreas(int mPreDIndex) {
        if (mPreDIndex > -1) {
            MrCityBe cityBe = provinceList.get(mPrePIndex).getCityList().get(mPreCIndex);
            curAreaData = cityBe.toArray();
        } else {
            MrCityBe cityBe = provinceList.get(mViewProvince.getCurrentItem()).getCityList().get(mViewCity.getCurrentItem());
            mCurrentCityName = cityBe.getName();
            curAreaData = cityBe.toArray();
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, curAreaData));
        mViewDistrict.setCurrentItem(mPreDIndex > -1 ? mPreDIndex : 0);
        mCurrentDistrictName = curAreaData[mPreDIndex > -1 ? mPreDIndex : 0];
    }
    protected void initProvinceDatas() {
        provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("tarea.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            MrXmlParserHandler handler = new MrXmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                String pId = "", cId = "", aId = "";
                if (isEditMode) {
                    pId = address.getProvince();
                    cId = address.getCity();
                    aId = address.getCounty();
                }
                mPrePIndex = getProvinceByName(pId);
                List<MrCityBe> cityList = provinceList.get(mPrePIndex).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mPreCIndex = getCityByName(cityList, cId);
                    List<MrDistrictBe> districtList = cityList.get(mPreCIndex).getDistrictList();
                    if (TextUtils.isEmpty(aId)) {
                        mPreDIndex = 0;
                        mCurrentDistrictName = districtList.get(0).getName();
                    } else {
                        mCurrentDistrictName = cId;
                        mPreDIndex = getItemIndexByName(districtList, mCurrentDistrictName);
                    }
                    mCurrentZipCode = districtList.get(mPreDIndex).getZipcode();
                }
            }
            //
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<MrCityBe> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
    private int getCityByName(List<MrCityBe> cityList, String cityName) {
        if (cityList != null) {
            if (TextUtils.isEmpty(cityName)) {
                mCurrentCityName = cityList.get(0).getName();
                return 0;
            } else {
                mCurrentCityName = cityName;
                return getItemIndexByName(cityList, cityName);
            }
        } else {
            mCurrentCityName = "";
            return -1;
        }
    }

    private int getProvinceByName(String provinceName) {
        if (provinceList != null) {
            if (TextUtils.isEmpty(provinceName)) {
                mCurrentProviceName = provinceList.get(0).getName();
                return 0;
            } else {
                mCurrentProviceName = provinceName;
                return getItemIndexByName(provinceList, provinceName);
            }
        } else {
            mCurrentProviceName = "";
            return -1;
        }
    }
    private int getItemIndexByName(List<? extends MrAddressBase> datas, String name) {
        int size = datas.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(datas.get(i).getName(), name)) {
                return i;
            }
        }

        return 0;
    }



    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities(-1);
        } else if (wheel == mViewCity) {
            updateAreas(-1);
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = curAreaData[newValue];
        }
    }
}
