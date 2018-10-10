package com.gokhanaliccii.citylist.data.datasource;

import com.gokhanaliccii.citylist.data.model.City;

import java.util.List;

public interface CityDataSource {

    List<City> getAllCities();

    List<City> getCitiesByName(String name);

    City getCityById(long id);
}
