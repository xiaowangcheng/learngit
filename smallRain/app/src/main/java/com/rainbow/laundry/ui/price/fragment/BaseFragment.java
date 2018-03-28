package com.rainbow.laundry.ui.price.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainbow.laundry.modle.good.Pro_good;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wyf on 2018/1/11.
 */

public class BaseFragment extends Fragment {
    //public List<HashMap<String ,List<Pro_good>>> mapArrayList =new ArrayList<HashMap<String ,List<Pro_good>>>();
    public Map<String,List<Pro_good>> mapArrayList = new HashMap<String,List<Pro_good>>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
