package com.gokhanaliccii.citylist.ui.cities.citylist;

import android.util.Log;

import com.gokhanaliccii.citylist.data.datasource.CityDataSource;
import com.gokhanaliccii.citylist.data.datasource.local.CityServiceLocator;

public class CityModel implements CityContract.Model {

    public static final String TAG = "CityModel";
    private CityContract.ViewModel mViewModel;
    private CityDataSource cityDataSource;


    public CityModel(CityContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;

        cityDataSource = CityServiceLocator.getInstance().getCityDataSource();
    }

    @Override
    public void loadAllCities() {
        mViewModel.onCitiesLoaded(cityDataSource.getAllCities());
    }

    @Override
    public void loadFilteredCities(String input) {
        Log.d(TAG, "loadFilteredCities: "+ input);
        mViewModel.onCitiesLoaded(cityDataSource.getFilteredCitiesByDisplayName(input));
    }

    @Override
    public void interrupt() {
        Log.d(TAG, "interrupt");
    }
}
