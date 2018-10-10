package com.gokhanaliccii.citylist.data.datasource.local;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gokhanaliccii.citylist.data.datasource.CityDataSource;
import com.gokhanaliccii.citylist.data.datasource.loader.DataLoadListener;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class LocalCitySource implements CityDataSource {

    private InputStream mCityInputStream;
    private List<City> cities;

    public LocalCitySource(InputStream cityInputStream) {
        this.mCityInputStream = cityInputStream;
        init();
    }

    private void init() {
        cities = new CityReader(mCityInputStream).readCities();
    }

    @Override
    public List<City> getAllCities() {
        return cities;
    }

    @Override
    public List<City> getFilteredCitiesByDisplayName(String keyword) {
        List<City> filteredCities = new LinkedList<>();

        for (City city : cities) {
            if (city == null) {
                continue;
            }

            if (StringUtil.isStartWithWithoutSensitivity(city.getDisplayName(), keyword)) {
                filteredCities.add(city);
            }
        }

        return filteredCities;
    }

    @Override
    public City getCityById(long id) {
        for (City city : cities) {
            if (city == null) {
                continue;
            }

            if (id == city.getId())
                return city;
        }

        return null;
    }

    @Override
    public void loadAllCitiesAsync(DataLoadListener.Multi<City> dataLoadListener) {
        if (dataLoadListener != null) {
            dataLoadListener.onDataLoaded(getAllCities());
        }
    }

    @Override
    public void getFilteredCitiesAsyncByDisplayName(String name, DataLoadListener.Multi<City> dataLoadListener) {
        if (dataLoadListener != null) {
            dataLoadListener.onDataLoaded(getFilteredCitiesByDisplayName(name));
        }
    }

    @Override
    public void getCityAsyncById(long id, DataLoadListener.Single<City> dataLoadListener) {
        if (dataLoadListener != null) {
            dataLoadListener.onDataLoaded(getCityById(id));
        }
    }

    private static class CityReader {
        private InputStream mCityInputStream;

        public CityReader(InputStream mCityInputStream) {
            this.mCityInputStream = mCityInputStream;
        }

        public List<City> readCities() {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<City>> cityListRef = new TypeReference<List<City>>() {
            };


            try {
                return objectMapper.readValue(mCityInputStream, cityListRef);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
