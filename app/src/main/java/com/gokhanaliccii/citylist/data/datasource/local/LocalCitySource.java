package com.gokhanaliccii.citylist.data.datasource.local;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gokhanaliccii.citylist.data.datasource.CityDataSource;
import com.gokhanaliccii.citylist.data.model.City;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.TreeMap;

public class LocalCitySource implements CityDataSource {

    private InputStream mCityInputStream;



    public LocalCitySource(InputStream cityInputStream) {
        this.mCityInputStream = cityInputStream;
    }

    private void init() {

    }

    @Override
    public List<City> getAllCities() {
        return new CityReader(mCityInputStream).readCities();
    }

    @Override
    public List<City> getCitiesByName(String name) {
        return null;
    }

    @Override
    public City getCityById(long id) {
        return null;
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
