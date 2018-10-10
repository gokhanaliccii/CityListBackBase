package com.gokhanaliccii.citylist.ui.cities.router;

import android.support.v4.app.FragmentManager;

import com.gokhanaliccii.citylist.R;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.ui.cities.detail.CityDetailFragment;

public class CityDetailRouter implements Router {

    private FragmentManager mFragmentManager;

    public CityDetailRouter(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void routeToCityDetail(City city) {
        mFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, CityDetailFragment.newInstance(city))
                .addToBackStack(null)
                .commit();
    }
}
