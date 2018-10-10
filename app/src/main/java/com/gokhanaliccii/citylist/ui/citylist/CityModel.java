package com.gokhanaliccii.citylist.ui.citylist;

import android.util.Log;

public class CityModel implements CityContract.Model {

    public static final String TAG = "CityModel";
    private CityContract.ViewModel mViewModel;

    public CityModel(CityContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void loadAllCities() {

    }

    @Override
    public void loadFilteredCities(String input) {

    }

    @Override
    public void interrupt() {
        Log.d(TAG, "interrupt");
    }
}
