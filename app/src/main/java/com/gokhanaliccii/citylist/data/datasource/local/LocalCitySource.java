package com.gokhanaliccii.citylist.data.datasource.local;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gokhanaliccii.citylist.data.datasource.CityDataSource;
import com.gokhanaliccii.citylist.data.datasource.loader.DataLoadListener;
import com.gokhanaliccii.citylist.data.model.City;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class LocalCitySource implements CityDataSource {

    private InputStream mCityInputStream;
    // Naturally sorted by key so it optimize searching cities
    private TreeMap<String, City> mCityNameCityMap;

    public LocalCitySource(InputStream cityInputStream) {
        this.mCityInputStream = cityInputStream;
        init();
    }

    private void init() {
        mCityNameCityMap = new TreeMap<>();

        List<City> cities = new CityReader(mCityInputStream).readCities();
        fillMap(cities);
    }

    private void fillMap(List<City> cities) {
        if (cities != null) {
            for (City city : cities) {
                if (city == null) {
                    continue;
                }
                mCityNameCityMap.put(city.getDisplayName().toLowerCase(), city);
            }
        }
    }

    @Override
    public List<City> getAllCities() {
        return new LinkedList<>(mCityNameCityMap.values());
    }

    @Override
    public List<City> getFilteredCitiesByDisplayName(String keyword) {
        String prefix = keyword.toLowerCase();
        return new LinkedList<>(mCityNameCityMap.subMap(prefix, prefix + Character.MAX_VALUE).values());
    }

    @Override
    public City getCityById(long id) {
        Collection<City> cities = mCityNameCityMap.values();

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
    public void loadFilteredCitiesAsyncByDisplayName(String name, DataLoadListener.Multi<City> dataLoadListener) {
        if (dataLoadListener != null) {
            dataLoadListener.onDataLoaded(getFilteredCitiesByDisplayName(name));
        }
    }

    @Override
    public void loadCityAsyncById(long id, DataLoadListener.Single<City> dataLoadListener) {
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
