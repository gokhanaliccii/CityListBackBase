package com.gokhanaliccii.citylist.data.datasource;

import com.gokhanaliccii.citylist.data.datasource.loader.DataLoadListener;
import com.gokhanaliccii.citylist.data.model.City;

import java.util.List;

public interface CityDataSource {

    List<City> getAllCities();

    List<City> getFilteredCitiesByDisplayName(String name);

    City getCityById(long id);

    void loadAllCitiesAsync(DataLoadListener.Multi<City> dataLoadListener);

    void getFilteredCitiesAsyncByDisplayName(String name, DataLoadListener.Multi<City> dataLoadListener);

    void getCityAsyncById(long id, DataLoadListener.Single<City> dataLoadListener);
}
