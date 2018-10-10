package com.gokhanaliccii.citylist.ui.cities.citylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanaliccii.citylist.base.BaseFragment;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.databinding.FragmentCityListBinding;
import com.gokhanaliccii.citylist.ui.cities.citylist.adapter.CityListAdapter;
import com.gokhanaliccii.citylist.ui.cities.router.CityDetailRouter;
import com.gokhanaliccii.citylist.util.ClickListener;

import java.util.Collections;
import java.util.List;

public class CityListFragment extends BaseFragment implements CityContract.View, ClickListener<City> {

    private static final String KEY_TYPED_TEXT = "typed_text";

    private FragmentCityListBinding mLayoutBinding;
    private CityListAdapter mCityListAdapter;

    private CityViewModel mViewModel;
    private CityDetailRouter mRouter;

    public static CityListFragment newInstance() {
        CityListFragment fragment = new CityListFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRouter = new CityDetailRouter(getFragmentManager());
        mViewModel = new CityViewModel(this);
        CityModel cityModel = new CityModel(mViewModel);
        mViewModel.setModel(cityModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutBinding == null) {
            mLayoutBinding = FragmentCityListBinding.inflate(inflater, container, false);
            mLayoutBinding.setViewModel(mViewModel);

            initRecyclerView();
        }

        return mLayoutBinding.getRoot();
    }

    private void initRecyclerView() {
        mLayoutBinding.recyclerViewCities.setHasFixedSize(true);
        mLayoutBinding.recyclerViewCities.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mLayoutBinding.recyclerViewCities.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        mCityListAdapter = new CityListAdapter(Collections.emptyList(), this);
        mLayoutBinding.recyclerViewCities.setAdapter(mCityListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mViewModel.loadAllCities();
        } else {
            mViewModel.searchInput.set(savedInstanceState.getString(KEY_TYPED_TEXT,""));
        }
    }

    @Override
    public void onCitiesLoaded(List<City> cities) {
        mCityListAdapter.updateCities(cities);
    }

    @Override
    public void onClick(City city) {
        mRouter.routeToCityDetail(city);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_TYPED_TEXT, mViewModel.searchInput.get());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLayoutBinding.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRouter = null;
    }
}
