package com.gokhanaliccii.citylist.ui.citylist.adapter;

import android.support.v7.util.DiffUtil;

import com.gokhanaliccii.citylist.data.model.City;

import java.util.List;

import static com.gokhanaliccii.citylist.util.CollectionUtil.getListItemCount;

public class CityListDiffUtil extends DiffUtil.Callback {

    private List<City> oldCityList;
    private List<City> newCityList;

    public CityListDiffUtil(List<City> oldCity, List<City> newCity) {
        this.oldCityList = oldCity;
        this.newCityList = newCity;
    }

    @Override
    public int getOldListSize() {
        return getListItemCount(oldCityList);
    }

    @Override
    public int getNewListSize() {
        return getListItemCount(newCityList);
    }

    @Override
    public boolean areItemsTheSame(int lhs, int rhs) {
        City oldCity = this.oldCityList.get(lhs);
        City newCity = this.newCityList.get(rhs);

        return oldCity.getId() == newCity.getId();
    }

    @Override
    public boolean areContentsTheSame(int lhs, int rhs) {
        City oldCity = this.oldCityList.get(lhs);
        City newCity = this.newCityList.get(rhs);

        return oldCity.getDisplayName().equals(newCity.getDisplayName());
    }
}
