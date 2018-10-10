package com.gokhanaliccii.citylist.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.citylist.common.BaseMapFragment;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.data.model.Coordinate;
import com.gokhanaliccii.citylist.databinding.FragmentCityDetailBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CityDetailFragment extends BaseMapFragment {

    private static final String KEY_CITY = "key_city";

    public static final int DEFAULT_ZOOM = 12;
    private City mCity;
    private FragmentCityDetailBinding mLayoutBinding;

    public static CityDetailFragment newInstance(City city) {
        CityDetailFragment fragment = new CityDetailFragment();
        fragment.mCity = city;
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutBinding == null) {
            mLayoutBinding = FragmentCityDetailBinding.inflate(inflater, container, false);
            loadGoogleMap(savedInstanceState);
        }

        if (savedInstanceState != null) {
            mCity = (City) savedInstanceState.getSerializable(KEY_CITY);
        }

        return mLayoutBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(KEY_CITY, mCity);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMapViewReady() {
        Coordinate coord = mCity.getCoord();
        LatLng latLng = new LatLng(coord.getLat(), coord.getLon());

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(mCity.getDisplayName());

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
        mGoogleMap.addMarker(markerOptions);
    }

    @Override
    public MapView getMapView() {
        return mLayoutBinding.mapView;
    }


}
