package com.gokhanaliccii.citylist.ui.citylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.citylist.common.BaseFragment;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.databinding.FragmentCityListBinding;
import com.gokhanaliccii.citylist.ui.citylist.adapter.CityListAdapter;

import java.util.Collections;
import java.util.List;

public class CityListFragment extends BaseFragment implements CityContract.View {

    private FragmentCityListBinding mLayoutBinding;
    private CityListAdapter mCityListAdapter;

    private CityContract.ViewModel mViewModel;

    public static CityListFragment newInstance() {
        CityListFragment fragment = new CityListFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new CityViewModel(this);
        CityModel cityModel = new CityModel(mViewModel);
        mViewModel.setModel(cityModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutBinding == null) {
            mLayoutBinding = FragmentCityListBinding.inflate(inflater, container, false);
            mCityListAdapter = new CityListAdapter(Collections.emptyList());
            mLayoutBinding.recyclerViewCities.setAdapter(mCityListAdapter);
        }

        return mLayoutBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mViewModel.loadAllCities();
        }
    }

    @Override
    public void onCitiesLoaded(List<City> cities) {

    }
}
