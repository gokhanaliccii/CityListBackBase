package com.gokhanaliccii.citylist.ui.citylist;

import com.gokhanaliccii.citylist.data.model.City;

import java.util.List;

public class CityViewModel implements CityContract.ViewModel<CityContract.Model> {

    private CityContract.View mView;
    private CityContract.Model mModel;

    public CityViewModel(CityContract.View mView) {
        this.mView = mView;
    }

    public void setModel(CityContract.Model model) {
        this.mModel = model;
    }

    @Override
    public void loadAllCities() {
        mModel.loadAllCities();
    }


    @Override
    public void filterCities(String input) {
        mModel.loadFilteredCities(input);
    }

    @Override
    public void onCitiesLoaded(List<City> cities) {
        mView.onCitiesLoaded(cities);
    }

    @Override
    public void interrupt() {
        mModel.interrupt();
    }
}
