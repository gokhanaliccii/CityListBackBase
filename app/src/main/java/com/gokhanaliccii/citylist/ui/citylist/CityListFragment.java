package com.gokhanaliccii.citylist.ui.citylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.citylist.common.BaseFragment;
import com.gokhanaliccii.citylist.databinding.FragmentCityListBinding;

public class CityListFragment extends BaseFragment {

    private FragmentCityListBinding mLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutBinding == null) {
            mLayoutBinding = FragmentCityListBinding.inflate(inflater, container, false);
        }


        return mLayoutBinding.getRoot();
    }
}
