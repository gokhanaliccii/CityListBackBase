package com.gokhanaliccii.citylist.ui.citylist.adapter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.databinding.CardCityBinding;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<City> mCityList;

    public CityListAdapter(List<City> mCityList) {
        this.mCityList = mCityList;
    }

    public void updateCities(List<City> cities) {
        new AsyncTask<List<City>, Void, DiffUtil.DiffResult>() {

            @Override
            protected DiffUtil.DiffResult doInBackground(List<City>... lists) {
                return DiffUtil.calculateDiff(new CityListDiffUtil(mCityList, cities));
            }

            @Override
            protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                super.onPostExecute(diffResult);

                diffResult.dispatchUpdatesTo(CityListAdapter.this);
                mCityList = cities;
            }
        }.execute(cities);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardCityBinding cityCardBinding = CardCityBinding.inflate(inflater, viewGroup, false);

        return new ViewHolder(cityCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        City city = mCityList.get(position);
        viewHolder.bind(city);
    }

    @Override
    public int getItemCount() {
        return mCityList == null
                ? 0
                : mCityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardCityBinding mCardBinding;

        public ViewHolder(@NonNull CardCityBinding cardBinding) {
            super(cardBinding.getRoot());

            this.mCardBinding = cardBinding;
        }

        public void bind(City city) {
            mCardBinding.setCity(city);
            mCardBinding.executePendingBindings();
        }

    }


}
