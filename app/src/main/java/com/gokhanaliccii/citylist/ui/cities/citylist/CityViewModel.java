package com.gokhanaliccii.citylist.ui.cities.citylist;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.util.Log;

import com.gokhanaliccii.citylist.data.model.City;

import java.util.List;

public class CityViewModel implements CityContract.ViewModel<CityContract.Model> {

    public ObservableField<String> searchInput = new ObservableField<>();

    private CityContract.View mView;
    private CityContract.Model mModel;

    public CityViewModel(CityContract.View mView) {
        this.mView = mView;

        searchInput.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mModel.loadFilteredCities(searchInput.get());
            }
        });
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
        Log.d("ViewModel", "onCitiesLoaded: "+ cities.size());
        mView.onCitiesLoaded(cities);
    }

    @Override
    public void interrupt() {
        mModel.interrupt();
    }
}
